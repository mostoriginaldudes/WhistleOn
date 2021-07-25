package io.hala.whistleon;

import io.hala.whistleon.domain.user.Position;
import io.hala.whistleon.domain.user.Role;
import io.hala.whistleon.domain.user.User;
import java.time.LocalDate;

public class ObjectHelper {
  public static User getFakeUser() {
    return User.builder()
        .userId(System.currentTimeMillis())
        .email("test@test.com")
        .name("test")
        .nickname("test")
        .role(Role.NONE)
        .position1(Position.CB)
        .position2(Position.CF)
        .weight(180)
        .height(180)
        .sido("서울특별시")
        .zonecode("11111")
        .roadAddress("강남대로")
        .password("123123")
        .phoneNum("010101111")
        .sigungu("강남구")
        .birthday(LocalDate.now())
        .description("test")
        .build();
  }
}
