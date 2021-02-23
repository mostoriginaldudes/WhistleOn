package io.hala.whistleon.service.login;

import io.hala.whistleon.common.exception.UnauthorizedException;

public interface JwtService {
    String createToken(String user);
    boolean isUsableToken(String jwt) throws UnauthorizedException;
    Object getToken(String key) throws Exception;
}
