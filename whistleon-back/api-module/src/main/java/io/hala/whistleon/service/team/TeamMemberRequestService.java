package io.hala.whistleon.service.team;

import io.hala.whistleon.controller.dto.RequestTeamMemberResponseDto;

public interface TeamMemberRequestService {

  void registTeamMember(Long teamId);

  RequestTeamMemberResponseDto findRequestTeamMembers(Long teamId);
}
