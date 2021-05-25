package io.hala.whistleon.service.user;

import io.hala.whistleon.controller.dto.SigninRequestDto;

public interface AuthService {
    void authEmail(String to);

    void authCode(String email, String code);

    long getCodeId(String email);

    void deleteCode(long id);

    String signin(SigninRequestDto signinRequestDto);
}
