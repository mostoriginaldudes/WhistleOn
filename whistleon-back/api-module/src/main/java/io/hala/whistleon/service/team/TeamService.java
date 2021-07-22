package io.hala.whistleon.service.team;

import io.hala.whistleon.controller.dto.TeamRegistRequestDto;
import io.hala.whistleon.controller.dto.TeamUpdateRequestDto;

public interface TeamService {

  void checkExistTeamEmail(String email);

  void registTeam(TeamRegistRequestDto teamRegistRequestDto);

  void updateTeam(String email, TeamUpdateRequestDto teamUpdateRequestDto);

  void registTeamMember(Long teamId);
}
