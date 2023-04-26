package com.manhtb.authservice.service.impl;

import com.manhtb.authservice.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    private final String SECRET_KEY;

    private final Long EXPIRE_TIME;

    JwtService(@Value("${jwt-secret_key}") String secretKey, @Value("${expire_time}") String expireTime) {
        SECRET_KEY = secretKey;
        EXPIRE_TIME = Long.valueOf(expireTime);
    }

    public String generateToken(User user) {
        Date now = new Date(System.currentTimeMillis());
        Date expire = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expire)
                .signWith(getSignKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Date getExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        String username = getUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public SecretKey getSignKey(String secretKey) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

}
