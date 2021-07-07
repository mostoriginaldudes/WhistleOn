package io.hala.whistleon.service.team;

import io.hala.whistleon.controller.dto.TeamRegistRequestDto;

public interface TeamService {

  void checkExistTeamEmail(String email);
  void registTeam(TeamRegistRequestDto teamRegistRequestDto);
}
