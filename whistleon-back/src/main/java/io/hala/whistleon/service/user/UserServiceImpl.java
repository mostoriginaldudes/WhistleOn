package io.hala.whistleon.service.user;

import io.hala.whistleon.common.exception.CustomException;
import io.hala.whistleon.common.exception.ExceptionCode;
import io.hala.whistleon.controller.dto.LoginResponseDto;
import io.hala.whistleon.controller.dto.SigninRequestDto;
import io.hala.whistleon.controller.dto.SignupRequestDto;
import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.domain.user.UserRepository;
import io.hala.whistleon.domain.user.UserStat;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  @Transactional
  @Override
  public void signup(SignupRequestDto signupRequestDto) {
    User user = signupRequestDto.makeUser(passwordEncoder);
    if (checkEmail(user.getEmail())) {
      user.addStat(new UserStat());
      userRepository.save(user);
    }
  }

  @Override
  public boolean checkEmail(String email) {
    Optional<User> findUser = userRepository.findByEmail(email);
    if (findUser.isPresent()) {
      throw new CustomException(ExceptionCode.DUPLICATE_DATA);
    }
    return true;
  }

  @Override
  public void checkExistNickname(String nickname) {
    Optional<User> findUser = userRepository.findUsersByNickname(nickname);
    if (findUser.isPresent()) {
      throw new CustomException(ExceptionCode.DUPLICATE_DATA);
    }
  }

  @Override
  public LoginResponseDto getLoginUserInfo(SigninRequestDto signinRequestDto) {
    User user = userRepository
        .findUserByEmailAndPassword(signinRequestDto.getEmail(), signinRequestDto.getPassword())
        .orElseThrow(() -> new CustomException(ExceptionCode.UNAUTHORIZED_MEMBER));
    return LoginResponseDto.builder()
        .userId(user.getUserId())
        .nickname(user.getNickname())
        .build();
  }
}
