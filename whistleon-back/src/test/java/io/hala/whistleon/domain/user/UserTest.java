package io.hala.whistleon.domain.user;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.hala.whistleon.common.exception.CustomException;
import io.hala.whistleon.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"spring.config.location=classpath:application-dev.properties"})
public class UserTest {

  @Autowired
  private UserService userService;

  @Test
  void checkNicknameTest() {
    String existNickname = "강상우";
    assertThatThrownBy(() -> userService.checkExistNickname(existNickname))
        .isInstanceOf(CustomException.class);

    String noneExistNickname = "neverEverUseThisNickname";
    assertThatCode(() -> userService.checkExistNickname(noneExistNickname))
        .doesNotThrowAnyException();
  }
}
