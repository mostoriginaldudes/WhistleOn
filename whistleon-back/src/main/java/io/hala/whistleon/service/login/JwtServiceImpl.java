package io.hala.whistleon.service.login;

import static java.nio.charset.StandardCharsets.UTF_8;

import io.hala.whistleon.common.exception.CustomException;
import io.hala.whistleon.common.exception.ExceptionCode;
import io.hala.whistleon.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {

  @Value("${jwt.salt.value}")
  private String SALT;

  /**
   * @param user
   * @return jwt token
   */
  @Override
  public String createToken(User user) {
    String jwt = Jwts.builder()
        .setHeaderParam("typ", "JWT")
        .setHeaderParam("regDate", System.currentTimeMillis())
        .setSubject(user.getEmail())
        .claim("userId", user.getUserId())
        .claim("email", user.getEmail())
        .claim("userName", user.getName())
        .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60)))
        .signWith(SignatureAlgorithm.HS256, this.generateKey())
        .compact();
    return jwt;
  }

  /**
   * @param jwt
   * @return checking token, then returns true or false.
   */
  @Override
  public boolean isUsableToken(String jwt) {
    try {
      Jws<Claims> claims = Jwts.parser()
          .setSigningKey(this.generateKey())
          .parseClaimsJws(jwt);
      return true;
    } catch (Exception e) {
      throw new CustomException(ExceptionCode.UNAUTHORIZED_MEMBER);
    }
  }

  private byte[] generateKey() {
    return SALT.getBytes(UTF_8);
  }

  @Override
  public Object getToken(String key) throws Exception {
    return null;
  }
}
