package io.hala.whistleon.service.team;

import io.hala.whistleon.controller.dto.RequestTeamMemberInfoDto;
import io.hala.whistleon.controller.dto.RequestTeamMembersResponseDto;

public interface TeamMemberRequestService {

  void registTeamMember(Long teamId);

  RequestTeamMembersResponseDto findRequestTeamMembers(Long teamId);

  RequestTeamMemberInfoDto findOneRequestTeamMember(Long userId, Long teamId);

  void approveTeamMember(Long teamId, Long userId);

  void rejectTeamMember(Long teamId, Long userId);
}
