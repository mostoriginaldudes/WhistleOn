package io.hala.whistleon.controller.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestTeamMembersResponseDto {

  private final List<RequestTeamMemberDto> requestUsers;
}
