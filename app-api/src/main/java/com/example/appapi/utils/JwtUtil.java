package com.example.appapi.utils;

import com.example.appapi.users.model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET = System.getenv("JWT_SECRET");
    private static final int EXP = Integer.parseInt(System.getenv("JWT_EXPIRED"));

    public static Users getUser(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return Users.builder()
                    .idx(claims.get("userIdx", Long.class))
                    .userId(claims.get("userId", String.class))
                    .email(claims.get("userEmail", String.class))
                    .userType(claims.get("userType", String.class))
                    .build();

        } catch (ExpiredJwtException e) {
            System.out.println("토큰이 만료되었습니다!");
            return null;
        }
    }

    public static String generateToken(Long userIdx, String userId, String userEmail, String userType) {
        Claims claims = Jwts.claims();
        claims.put("userIdx", userIdx);
        claims.put("userId", userId);
        claims.put("userEmail", userEmail);
        claims.put("userType", userType);
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXP))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        return token;
    }

    public static boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("토큰이 만료되었습니다!");
            return false;
        }
        return true;
    }
}
