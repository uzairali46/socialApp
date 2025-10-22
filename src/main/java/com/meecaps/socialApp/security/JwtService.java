package com.meecaps.socialApp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {


    private final static String SECRET_KEY="123e4nkd9485760@kw38560924001n880" ;

    public SecretKey getSecretKey(){

        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateAccessToken(String email, String role) {
        return Jwts.builder()
                .subject(email)
                .claim("Role ", role)
//                .claim("AccessToken", String.class)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60* 60))
                .signWith(getSecretKey())
                .compact();
    }

    // Extract email
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }


    // Extract role
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }



    // Validate token
    public boolean isTokenValid(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration().after(new Date());
    }




     /*

    Purpose of this method:
      This method reads and verifies a JWT token and returns all the data inside it (called claims)
       — like email, roles, issue time, and expiration.
     */

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }



}


