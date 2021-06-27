package io.hala.whistleon.jwt;

import io.hala.whistleon.controller.dto.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  private static final long TOKEN_EXPIRE_TIME = 1000 * 60 * 60;
  private final Key key;

  public JwtTokenProvider(@Value("${jwt.salt.value}") String salt) {
    this.key = this.generateKey(salt);
  }

  public TokenDto createToken(Authentication authentication) {
    String grantedAuthorities = authentication.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining());

    Date tokenExpires = new Date(new Date().getTime() + TOKEN_EXPIRE_TIME);
    String token = Jwts.builder()
        .setSubject(authentication.getName())
        .claim("ROLE", grantedAuthorities)
        .setExpiration(tokenExpires)
        .signWith(this.key, SignatureAlgorithm.HS256)
        .compact();

    return TokenDto.builder()
        .token(token)
        .tokenExpires(tokenExpires.getTime())
        .build();
  }

  public boolean isValidToken(String token) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(this.key)
          .build()
          .parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      // Todo Exception별로 로그 추가
    }
    return false;
  }

  public Authentication getAuthentication(String token) {
    Claims claims = this.parseToken(token);
    String authorities = (String) claims.get("ROLE");
    Collection<? extends GrantedAuthority> grantedAuthorities = List
        .of(new SimpleGrantedAuthority(authorities));
    UserDetails principal = new User(claims.getSubject(), "", grantedAuthorities);

    return new UsernamePasswordAuthenticationToken(principal, "", grantedAuthorities);
  }

  private Claims parseToken(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(this.key)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key generateKey(String salt) {
    byte[] keyBytes = Decoders.BASE64.decode(salt);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
