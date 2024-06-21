package com.parth.rbac.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtTokenUtil {

   @Value("${application.security.jwt.secret-key}")
   private String SECRET_KEY;

   @Value("${application.security.jwt.expiration}")
   private long jwt_expiration;

   private Key key;

   @PostConstruct
   public void getSignInKey() {
      byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
      this.key = Keys.hmacShaKeyFor(keyBytes);
   }

   public String extractUsername(String token) {
      return extractClaim(token, Claims::getSubject);
   }

   public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
      final Claims claims = extractAllClaims(token);
      return claimResolver.apply(claims);
   }

   public Claims extractAllClaims(String token) {
      return Jwts.parser().verifyWith((SecretKey) key)
           .build().parseSignedClaims(token).getPayload();
   }

   public String generateToken(UserDetails userDetails) {
      return generateToken(new HashMap<>(), userDetails);
   }

   public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
      return buildToken(claims, userDetails, jwt_expiration);
   }

   private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long jwt_expiration) {
      var authorities = userDetails.getAuthorities()
           .stream().map(GrantedAuthority::getAuthority)
           .collect(Collectors.toSet());

      return Jwts.builder().claims(extraClaims).subject(userDetails.getUsername())
           .issuedAt(new Date(System.currentTimeMillis()))
           .expiration(new Date(System.currentTimeMillis() + jwt_expiration))
           .claim("authorities", authorities)
           .signWith(key)
           .compact();
   }

   public boolean isTokenValid(String token, UserDetails userDetails) {
      final String username = extractUsername(token);
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
   }

   public boolean isTokenExpired(String token) {
      return extractExpiration(token).before(new Date());
   }

   public Date extractExpiration(String token) {
      return extractClaim(token, Claims::getExpiration);
   }

   /*private Key getSignInKey() {
      byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
      return Keys.hmacShaKeyFor(keyBytes);
   }*/
}
