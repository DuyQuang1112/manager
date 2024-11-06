package com.myproject.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class JwtTokenProvider {
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public String generateToken(CustomUserDetails customUserDetails) {
        SecretKey key = Keys.hmacShaKeyFor(SIGNER_KEY.getBytes());
        return Jwts.builder()
                .setSubject(customUserDetails.getUsername())
                .claim("role",customUserDetails.getRoleName())
                .setIssuer("OceanTech")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}
