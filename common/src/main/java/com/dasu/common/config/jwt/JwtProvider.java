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
    private final JWTVerifier verifier;

    public JwtProvider(String secret, Long expirationMillis) {
        this.secret = secret;
        this.expirationMillis = expirationMillis;
        this.algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algorithm).build();

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


    public boolean validateToken(String token) {
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return false;
        }
        verifier.verify(deleteBearerToken(token));
        return true;
    }
    public String getSubject(String token) {
        if(token.startsWith("Bearer ")) {
            return verifier.verify(deleteBearerToken(token)).getSubject();
        }else{
            return verifier.verify(token).getSubject();
        }
    }

    private String deleteBearerToken(String token) {
        return token.substring(7);
    }

    public DecodedJWT getDecodeJwt(String token) {
        return verifier.verify(deleteBearerToken(token));
    }



}
