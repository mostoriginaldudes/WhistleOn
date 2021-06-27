package io.hala.whistleon.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfo {

  private final String phoneNum;
  private final String sido;
  private final String sigungu;
  private final String roadAddress;
  private final String zonecode;
  private final Position position1;
  private final Position position2;
  private final int height;
  private final int weight;
  private final String nickname;
  private final String description;

}
