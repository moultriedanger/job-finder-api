package com.moultriedanger.mljobfinder.user.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;                  // Base64-encoded HS256 secret (>= 256 bits)

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;               // in milliseconds

    /** Extract the principal (we use email) from the token. */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = parseClaims(token);
        return claimsResolver.apply(claims);
    }

    /** Generate a token for a UserDetails; UserDetails#getUsername() should return the email. */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    /** Optional convenience if you want to issue directly from an email. */
    public String generateTokenForEmail(String email, Duration ttl) {
        long expMs = ttl == null ? jwtExpiration : ttl.toMillis();
        return buildTokenFromSubject(email, expMs, new HashMap<>());
    }

    public long getExpirationTime() {
        return jwtExpiration;
    }

    /* ---------- Internals ---------- */

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expirationMs) {
        // Normalize principal to email-in-lowercase
        String subjectEmail = userDetails.getUsername() == null
                ? null
                : userDetails.getUsername().toLowerCase(Locale.ROOT);
        return buildTokenFromSubject(subjectEmail, expirationMs, extraClaims);
    }

    private String buildTokenFromSubject(String subjectEmail, long expirationMs, Map<String, Object> extraClaims) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(subjectEmail)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String subject = extractUsername(token); // verifies signature via parse
        if (subject == null || userDetails.getUsername() == null) return false;
        return subject.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /** Parses & verifies signature (and exp); throws on invalid/expired tokens. */
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .setAllowedClockSkewSeconds(60) // small skew tolerance
                .build()
                .parseClaimsJws(token)          // verifies signature
                .getBody();
    }

    private Key getSignInKey() {
        // Secret must be Base64-encoded; length >= 256 bits for HS256.
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
