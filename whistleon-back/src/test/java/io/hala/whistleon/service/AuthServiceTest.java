package io.hala.whistleon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.hala.whistleon.common.exception.CustomException;
import io.hala.whistleon.controller.dto.LoginResponseDto;
import io.hala.whistleon.controller.dto.SigninRequestDto;
import io.hala.whistleon.controller.dto.SignupRequestDto;
import io.hala.whistleon.domain.user.Position;
import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.domain.user.UserRepository;
import io.hala.whistleon.service.user.AuthService;
import io.hala.whistleon.service.user.UserService;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(properties = {"spring.config.location=classpath:application-dev.properties"})
@Transactional
public class AuthServiceTest {

  @Autowired
  private AuthService authService;

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;


  private final String email = "test@test.com";
  private final String name = "테스트";
  private final LocalDate birthday = LocalDate.now();
  private final String phoneNum = "01012341234";
  private final String sido = "인천광역시";
  private final String sigungu = "인천광역시 미추홀구";
  private final String zonecode = "12345";
  private final String roadAddress = "인천광역시 미추홀구 남동대로 111";
  private final int height = 180;
  private final int weight = 70;
  private final String nickname = "테스트닉넴";
  private final String password = "testpassword123!";
  private final Position position1 = Position.CB;
  private final Position position2 = Position.CF;
  private final String description = "안녕하세요 테스트입니다";

  private final String failEmail = "failtest@test.com";
  private final String failPassword = "failtestpassword123!";

  @BeforeEach
  void signup() {
    SignupRequestDto signupRequestDto = SignupRequestDto.builder()
        .email(email)
        .name(name)
        .birthday(birthday)
        .phoneNum(phoneNum)
        .sido(sido)
        .sigungu(sigungu)
        .zonecode(zonecode)
        .roadAddress(roadAddress)
        .height(height)
        .weight(weight)
        .nickname(nickname)
        .password(password)
        .position1(position1)
        .position2(position2)
        .description(description)
        .build();

    userService.signUp(signupRequestDto);
  }

  @Test
  void afterSignup() {
    Optional<User> optUser = userRepository.findByEmail(email);
    assertThat(optUser.isPresent()).isTrue();
    User testUser = optUser.get();
    assertThat(testUser.getEmail()).isEqualTo(email);
    assertThat(passwordEncoder.matches(password, testUser.getPassword())).isTrue();
  }

  @Test
  void duplicateSignUp() {
    SignupRequestDto signupRequestDto = SignupRequestDto.builder()
        .email(email)
        .name(name)
        .birthday(birthday)
        .phoneNum(phoneNum)
        .sido(sido)
        .sigungu(sigungu)
        .zonecode(zonecode)
        .roadAddress(roadAddress)
        .height(height)
        .weight(weight)
        .nickname(nickname)
        .password(password)
        .position1(position1)
        .position2(position2)
        .description(description)
        .build();

    assertThatThrownBy(() -> userService.signUp(signupRequestDto))
        .isInstanceOf(CustomException.class);
  }

  @Test
  void successLogin() {
    SigninRequestDto signinRequestDto = SigninRequestDto.builder()
        .email(email)
        .password(password)
        .build();

    LoginResponseDto loginResponseDto = authService.signIn(signinRequestDto);
    assertThat(loginResponseDto.getToken()).isNotNull();
    assertThat(loginResponseDto.getTokenExpires()).isNotNull();
  }

  @Test
  void failLoginByEmail() {
    SigninRequestDto signinRequestDto = SigninRequestDto.builder()
        .email(failEmail)
        .password(password)
        .build();

    assertThatThrownBy(() -> authService.signIn(signinRequestDto))
        .isInstanceOf(RuntimeException.class);
  }

  @Test
  void failLoginByPassword() {
    SigninRequestDto signinRequestDto = SigninRequestDto.builder()
        .email(email)
        .password(failPassword)
        .build();

    assertThatThrownBy(() -> authService.signIn(signinRequestDto))
        .isInstanceOf(RuntimeException.class);
  }
}
