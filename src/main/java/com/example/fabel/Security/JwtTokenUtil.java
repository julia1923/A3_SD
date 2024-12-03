package com.example.fabel.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private final String SECRETKEY = "82a70b084a11b6da0fc3c0f79f3b2289182e9413d898fe7723da0ec58df362de";
    private final long EXPIRATION_TIME = 86400000; // 1 dia em milissegundos

    public String generateToken(String username,String email, String userid) {
        return Jwts.builder()
                .setSubject(username)
                .claim("email", email)
                .claim("id", userid)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRETKEY)
                .compact();
    }

    public boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
            .setSigningKey(SECRETKEY)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(getUsernameFromToken(token)) && !isTokenExpired(token));
    }
}