package io.hala.whistleon.qna;


import static org.assertj.core.api.Assertions.assertThat;

import io.hala.whistleon.controller.dto.QnaRegistRequestDto;
import io.hala.whistleon.controller.dto.SignupRequestDto;
import io.hala.whistleon.domain.qna.Qna;
import io.hala.whistleon.domain.qna.QnaRepository;
import io.hala.whistleon.domain.user.Position;
import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.service.PrincipalHelper;
import io.hala.whistleon.service.qna.QnaService;
import io.hala.whistleon.service.user.UserService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class QnaTest {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private PrincipalHelper principalHelper;

  @Autowired
  private QnaRepository qnaRepository;

  @Autowired
  private QnaService qnaService;

  @Autowired
  private UserService userService;


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
    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "",
        List.of(new SimpleGrantedAuthority("NONE")));

    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  @Test
  void registQnaTest() {
    String title = "qna 테스트";
    String content = "qna 테스트 본문";

    long qnaId = qnaService.registQna(QnaRegistRequestDto.builder()
        .title(title)
        .content(content)
        .build()).getQnaId();

    Qna qna = qnaRepository.findById(qnaId)
        .orElseThrow(() -> new RuntimeException());

    User user = principalHelper.getLoginUser();
    assertThat(qna.getUser()).isEqualTo(user);
    assertThat(qna.getTitle()).isEqualTo(title);
    assertThat(qna.getContent()).isEqualTo(content);
  }

}
