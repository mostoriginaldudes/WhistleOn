package io.hala.whistleon.service.user;

import io.hala.whistleon.controller.dto.SigninRequestDto;
import io.hala.whistleon.controller.dto.TokenDto;

public interface AuthService {

  void authEmail(String to);

  void authCode(String email, String code);

  long getCodeId(String email);

  void deleteCode(long id);

  TokenDto signin(SigninRequestDto signinRequestDto);
}
