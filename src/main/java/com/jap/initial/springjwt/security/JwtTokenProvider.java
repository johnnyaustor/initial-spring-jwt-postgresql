package com.jap.initial.springjwt.security;

import com.jap.initial.springjwt.model.Users;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-time}")
    private Long expiration;

    public String generateToken(Authentication authentication) {
        final Users users = (Users) authentication.getPrincipal();

        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + expiration);
        String userId = Long.toString(users.getId());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userId);
        claims.put("email", users.getEmail());
        claims.put("fullName", users.getFullName());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();


    }

    boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.out.println("Invalid Jwt Signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid Jwt Token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired Jwt Token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported Jwt token");
        } catch (IllegalArgumentException ex) {
            System.out.println("Jwt claim string is empty");
        }
        return false;
    }

    // Get user id by token
    Long getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String id = (String) claims.get("id");
        return Long.parseLong(id);
    }
}
