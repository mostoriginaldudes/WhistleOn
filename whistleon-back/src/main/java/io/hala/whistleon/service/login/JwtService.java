package io.hala.whistleon.service.login;

import io.hala.whistleon.domain.user.User;

public interface JwtService {
    String createToken(User user);
    boolean isUsableToken(String jwt);
    Object getToken(String key) throws Exception;
}
