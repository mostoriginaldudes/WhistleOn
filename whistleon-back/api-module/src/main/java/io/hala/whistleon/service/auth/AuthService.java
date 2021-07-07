package io.hala.whistleon.service.auth;

import io.hala.whistleon.controller.dto.LoginResponseDto;
import io.hala.whistleon.controller.dto.SigninRequestDto;

public interface AuthService {

  void authEmail(String to);

  void authCode(String email, String code);

  long getCodeId(String email);

  void deleteCode(long id);

  LoginResponseDto signIn(SigninRequestDto signinRequestDto);
}
