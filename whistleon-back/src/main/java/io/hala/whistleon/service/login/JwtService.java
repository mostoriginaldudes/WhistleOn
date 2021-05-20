package io.hala.whistleon.service.login;

public interface JwtService {
    String createToken(String user);
    boolean isUsableToken(String jwt) throws UnauthorizedException;
    Object getToken(String key) throws Exception;
}
