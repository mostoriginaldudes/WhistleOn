package io.hala.whistleon.controller.dto;

import io.hala.whistleon.domain.user.Position;
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

}
