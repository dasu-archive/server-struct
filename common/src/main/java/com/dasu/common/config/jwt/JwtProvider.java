package com.dasu.common.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.Map;

public class JwtProvider {

    private final String secret;
    private final Long expirationMillis;
    private final Algorithm algorithm;

    public JwtProvider(String secret, Long expirationMillis) {
        this.secret = secret;
        this.expirationMillis = expirationMillis;
        this.algorithm = Algorithm.HMAC256(secret);

    }

    public String generateToken(String subject, Map<String, Object> claims) {

        JWTCreator.Builder builder = JWT.create()
                .withSubject(subject)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + this.expirationMillis));

        if(claims != null && !claims.isEmpty()) {
            claims.forEach((k, v) -> builder.withClaim(k, v.toString()));
        }

        return builder.sign(this.algorithm);

    }

    public DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(this.algorithm).build();
        return verifier.verify(token); // 예외 발생 시 검증 실패
    }

    public String getSubject(String token) {
        return verifyToken(token).getSubject();
    }

}
