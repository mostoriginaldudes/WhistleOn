package io.hala.whistleon.user;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hala.whistleon.controller.dto.CheckUserRequestDto;
import io.hala.whistleon.controller.dto.LoginResponseDto;
import io.hala.whistleon.controller.dto.SigninRequestDto;
import io.hala.whistleon.controller.dto.SignupRequestDto;
import io.hala.whistleon.domain.user.Position;
import io.hala.whistleon.exception.CustomException;
import io.hala.whistleon.service.user.AuthService;
import io.hala.whistleon.service.user.UserService;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserTest {

  @Autowired
  private UserService userService;

  @Autowired
  private AuthService authService;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

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
  private final String failNickname = "neverEverUseThisNickname";
  private final String failPassword = "fail" + this.password;

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
  void checkNicknameTest() {
    assertThatThrownBy(() -> userService.checkExistNickname(this.nickname))
        .isInstanceOf(CustomException.class);

    assertThatCode(() -> userService.checkExistNickname(this.failNickname))
        .doesNotThrowAnyException();
  }

  @Test
  void getUserInfoTest() throws Exception {
    LoginResponseDto loginResponseDto = authService.signIn(SigninRequestDto.builder()
        .email(this.email)
        .password(this.password)
        .build());

    String token = loginResponseDto.getToken();

    mockMvc.perform(get("/users/email/" + this.email)
        .header("Authorization", token))
        .andExpect(status().isOk());
  }

  @Test
  void getUserInfoFailTest() throws Exception {
    LoginResponseDto loginResponseDto = authService.signIn(SigninRequestDto.builder()
        .email(this.email)
        .password(this.password)
        .build());

    String token = loginResponseDto.getToken();

    mockMvc.perform(get("/users/email/" + this.failEmail)
        .header("Authorization", token))
        .andExpect(status().isBadRequest());
  }

  @Test
  void checkUserInfoSuccessTest() throws Exception {
    LoginResponseDto loginResponseDto = authService.signIn(SigninRequestDto.builder()
        .email(this.email)
        .password(this.password)
        .build());

    String token = loginResponseDto.getToken();
    String body = objectMapper.writeValueAsString(CheckUserRequestDto.builder()
        .email(this.email)
        .password(this.password)
        .build());

    mockMvc.perform(post("/users/checkInfo")
        .header("Authorization", token)
        .content(body)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

  }

  @Test
  void checkUserInfoFailTest() throws Exception {
    LoginResponseDto loginResponseDto = authService.signIn(SigninRequestDto.builder()
        .email(this.email)
        .password(this.password)
        .build());

    String token = loginResponseDto.getToken();
    String body = objectMapper.writeValueAsString(CheckUserRequestDto.builder()
        .email(this.email)
        .password(this.failPassword)
        .build());

    mockMvc.perform(post("/users/checkInfo")
        .header("Authorization", token)
        .content(body)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }
}

