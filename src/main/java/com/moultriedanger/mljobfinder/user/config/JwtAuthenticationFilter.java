package com.moultriedanger.mljobfinder.user.config;

import com.moultriedanger.mljobfinder.user.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Validates Bearer tokens. Behavior:
 *  - /auth/** is skipped.
 *  - No Authorization header -> pass through (entry point will 401 if endpoint requires auth).
 *  - Invalid/expired token -> 401 and STOP.
 *  - Valid token -> set SecurityContext and continue.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain chain
    ) throws ServletException, IOException {

        final String path = request.getServletPath();
        // 1) Never interfere with auth endpoints
        if (path != null && path.startsWith("/auth/")) {
            chain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");

        // 2) No Bearer header -> pass through; secured endpoints will 401 via entry point
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);

        try {
            // IMPORTANT: extractUsername MUST verify signature & expiry (see JwtService)
            final String username = jwtService.extractUsername(token);

            // If we already have auth in context, just continue
            Authentication existing = SecurityContextHolder.getContext().getAuthentication();
            if (username != null && existing == null) {
                UserDetails user = userDetailsService.loadUserByUsername(username);

                if (!jwtService.isTokenValid(token, user)) {
                    // 3) Invalid token -> 401 and STOP
                    SecurityContextHolder.clearContext();
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                    return;
                }

                // 4) Valid token -> set context and continue
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            chain.doFilter(request, response);
        } catch (Exception ex) {
            // Any parsing/validation error -> 401 (don't swallow and return 200)
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        }
    }
}
