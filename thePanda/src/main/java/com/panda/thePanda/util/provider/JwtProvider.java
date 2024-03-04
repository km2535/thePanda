package com.panda.thePanda.util.provider;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.nimbusds.jose.util.StandardCharset;

import org.springframework.beans.factory.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
  @Value("${jwt.secret.key}")
  private String secretKey;

  public String create(String userId) {
    Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
    Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharset.UTF_8));

    String jwt = Jwts.builder()
        .signWith(key, SignatureAlgorithm.HS256)
        .setSubject(userId).setIssuedAt(new Date()).setExpiration(expiredDate).compact();

    return jwt;
  }

  public String validate(String jwt) {
    String subject = null;
    Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharset.UTF_8));
    try {
      subject = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(jwt)
          .getBody()
          .getSubject();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return subject;
  }
}