package com.dasu.jwt;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtTest {
    private final String secret = "test-secret-key";
    private final long expiration = 1000 * 60 * 60; // 1시간
    private final JwtProvider jwtProvider = new JwtProvider(secret, expiration);

    @Test
    void 토큰_생성_및_검증() {
        // given
        String subject = "user123";
        String role = "ADMIN";

        // when
        String token = jwtProvider.generateToken(subject, null);
        DecodedJWT decodedJWT = jwtProvider.verifyToken(token);

        // then
        assertEquals(subject, decodedJWT.getSubject());
        assertNotNull(decodedJWT.getExpiresAt());
    }

    @Test
    void 잘못된_토큰은_예외를_던짐() {
        String invalidToken = "malformed.token.value";

        assertThrows(JWTVerificationException.class, () -> {
            jwtProvider.verifyToken(invalidToken);
        });
    }
}
