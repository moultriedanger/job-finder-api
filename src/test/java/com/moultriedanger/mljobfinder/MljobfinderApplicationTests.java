package com.moultriedanger.mljobfinder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
@ActiveProfiles("test")
class MljobfinderApplicationTests {

	@MockitoBean
	JwtDecoder jwtDecoder;     // satisfies resource server JWT

	@MockitoBean
	JavaMailSender mailSender; // avoids real SMTP on startup

	@Test
	void contextLoads() {}
}