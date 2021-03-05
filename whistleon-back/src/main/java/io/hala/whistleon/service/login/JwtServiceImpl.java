package io.hala.whistleon.service.login;

import io.hala.whistleon.common.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import static java.nio.charset.StandardCharsets.UTF_8;

@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.header.auth}")
    private String HEADER_AUTH;

    @Value("${jwt.salt.value}")
    private String SALT;

    /**
     *
     * @param user
     * @return jwt token
     */
    @Override
    public String createToken(String user) {
//        String jwt = Jwts.builder()
//                .setHeaderParam("typ", "JWT")
//                .setHeaderParam("regDate", System.currentTimeMillis())
//                .setSubject(user.getEmail())
//                .claim("userId", user.getUserId())
//                .claim("email", user.getEmail())
//                .claim("userName", user.getName())
//                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60)))
//                .signWith(SignatureAlgorithm.HS256, this.generateKey())
//                .compact();
//        return jwt;
        return null;
    }

    /**
     *
     * @param jwt
     * @return checking token, then returns true or false.
     * @throws UnauthorizedException
     */
    @Override
    public boolean isUsableToken(String jwt) throws UnauthorizedException {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(this.generateKey())
                    .parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            throw new UnauthorizedException();
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
