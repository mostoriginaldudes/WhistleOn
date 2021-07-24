package io.hala.whistleon.controller.dto;

import io.hala.whistleon.domain.team.TeamMemberRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestTeamMemberDto {

  private Long requestId;
  private Long userId;
  private String nickname;

  public static RequestTeamMemberDto of(TeamMemberRequest teamMemberRequest) {
    return RequestTeamMemberDto.builder()
        .requestId(teamMemberRequest.getId())
        .userId(teamMemberRequest.getUser().getUserId())
        .nickname(teamMemberRequest.getUser().getNickname())
        .build();
  }
}
