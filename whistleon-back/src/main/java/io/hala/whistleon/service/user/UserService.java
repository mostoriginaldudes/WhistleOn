package io.hala.whistleon.service.user;

import io.hala.whistleon.controller.dto.SignupRequestDto;
import io.hala.whistleon.controller.dto.UserInfoResponseDto;

public interface UserService {

  void signUp(SignupRequestDto signupRequestDto);

  boolean checkEmail(String email);

  void checkExistNickname(String nickname);

  UserInfoResponseDto getUserInfo(String email);
}
