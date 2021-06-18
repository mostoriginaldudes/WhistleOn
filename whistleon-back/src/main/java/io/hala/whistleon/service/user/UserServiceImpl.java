package io.hala.whistleon.service.user;

import io.hala.whistleon.common.exception.CustomException;
import io.hala.whistleon.common.exception.ExceptionCode;
import io.hala.whistleon.common.util.PrincipalHelper;
import io.hala.whistleon.controller.dto.CheckUserRequestDto;
import io.hala.whistleon.controller.dto.SignupRequestDto;
import io.hala.whistleon.controller.dto.UpdateUserInfoRequestDto;
import io.hala.whistleon.controller.dto.UserInfoResponseDto;
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
  private final PrincipalHelper principalHelper;

  @Transactional
  @Override
  public void signUp(SignupRequestDto signupRequestDto) {
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
  public UserInfoResponseDto getUserInfo(String email) {
    String loginEmail = principalHelper.getName();
    User user = null;
    if (matchesEmail(loginEmail, email)) {
      user = userRepository.findByEmail(email)
          .orElseThrow(() -> new CustomException(ExceptionCode.INVALID_FORM_DATA));
    }
    return UserInfoResponseDto.builder()
        .email(user.getEmail())
        .nickname(user.getNickname())
        .birthday(user.getBirthday())
        .height(user.getHeight())
        .weight(user.getWeight())
        .name(user.getName())
        .phoneNum(user.getPhoneNum())
        .description(user.getDescription())
        .position1(user.getPosition1())
        .position2(user.getPosition2())
        .roadAddress(user.getRoadAddress())
        .sido(user.getSido())
        .sigungu(user.getSigungu())
        .zonecode(user.getZonecode())
        .build();
  }

  @Override
  public void checkUserInfo(CheckUserRequestDto checkUserRequestDto) {
    String userEmail = principalHelper.getName();
    if (matchesEmail(userEmail, checkUserRequestDto.getEmail())) {
      User user = userRepository
          .findByEmail(userEmail)
          .orElseThrow(() -> new CustomException(ExceptionCode.INVALID_FORM_DATA));
      if (!passwordEncoder.matches(checkUserRequestDto.getPassword(), user.getPassword())) {
        throw new CustomException(ExceptionCode.INVALID_FORM_DATA);
      }
    }
  }

  @Override
  @Transactional
  public void updateUserInfo(String email, UpdateUserInfoRequestDto updateUserInfoRequestDto) {
    String userEmail = principalHelper.getName();
    if (matchesEmail(userEmail, email)) {
      checkExistNickname(updateUserInfoRequestDto.getNickname());

      User user = userRepository.findByEmail(email)
          .orElseThrow(() -> new CustomException(ExceptionCode.RESOURCES_NOT_EXIST));

      user.updateUserUsingUpdateInfo(updateUserInfoRequestDto);
      userRepository.save(user);
    }
  }

  @Override
  public void deleteUser(String email) {
    String userEmail = principalHelper.getName();
    if (matchesEmail(userEmail, email)) {
      User user = userRepository.findByEmail(email)
          .orElseThrow(() -> new CustomException(ExceptionCode.RESOURCES_NOT_EXIST));

      userRepository.delete(user);
    }
  }

  private boolean matchesEmail(String loginEmail, String requestEmail) {
    if (!loginEmail.equals(requestEmail)) {
      throw new CustomException(ExceptionCode.INVALID_FORM_DATA);
    }
    return true;
  }
}
