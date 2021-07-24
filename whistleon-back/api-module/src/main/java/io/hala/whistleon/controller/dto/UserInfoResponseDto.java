package io.hala.whistleon.controller.dto;

import io.hala.whistleon.domain.user.Position;
import io.hala.whistleon.domain.user.User;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoResponseDto {

  private String email;

  private String name;

  private LocalDate birthday;

  private String phoneNum;

  private String sido;

  private String sigungu;

  private String zonecode;

  private String roadAddress;

  private int height;

  private int weight;

  private String nickname;

  private Position position1;

  private Position position2;

  private String description;

  public static UserInfoResponseDto of(User user) {
    return UserInfoResponseDto.builder()
        .email(user.getEmail())
        .name(user.getName())
        .birthday(user.getBirthday())
        .phoneNum(user.getPhoneNum())
        .sido(user.getSido())
        .sigungu(user.getSigungu())
        .zonecode(user.getZonecode())
        .roadAddress(user.getRoadAddress())
        .height(user.getHeight())
        .weight(user.getWeight())
        .nickname(user.getNickname())
        .position1(user.getPosition1())
        .position2(user.getPosition2())
        .description(user.getDescription())
        .build();
  }
}
