package io.hala.whistleon.service.team;

import io.hala.whistleon.controller.dto.TeamRegistRequestDto;
import io.hala.whistleon.domain.team.Team;
import io.hala.whistleon.domain.team.TeamRepository;
import io.hala.whistleon.domain.team.TeamStat;
import io.hala.whistleon.domain.user.Role;
import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.exception.CustomException;
import io.hala.whistleon.exception.ExceptionCode;
import io.hala.whistleon.service.PrincipalHelper;
import io.hala.whistleon.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {

  private final String TEAM_LOGO_DIR = "/team";
  private final PrincipalHelper principalHelper;
  private final TeamRepository teamRepository;
  private final FileUtil fileUtil;

  @Override
  public void checkExistTeamEmail(String email) {
    this.checkExistTeamByEmail(email);
  }

  @Transactional
  @Override
  public void registTeam(TeamRegistRequestDto teamRegistRequestDto) {
    User loginUser = principalHelper.getLoginUser();
    this.checkExistTeamByEmail(teamRegistRequestDto.getEmail());

    if (loginUser.hasNotTeam()) {
      String logo = fileUtil.uploadFile(teamRegistRequestDto.getLogo(), TEAM_LOGO_DIR);

      Team team = this.makeTeam(teamRegistRequestDto, logo);
      team.addTeamStat(new TeamStat());

      loginUser.createTeam(team);
      teamRepository.save(team);
    }
  }

  /**
   * 이 아래로는 private method
   */

  private void checkExistTeamByEmail(String email) {
    Team team = teamRepository.findByEmail(email)
        .orElse(null);
    if (team != null) {
      throw new CustomException(ExceptionCode.DUPLICATE_TEAM_EMAIL);
    }
  }
  private Team makeTeam(TeamRegistRequestDto teamRegistRequestDto, String logo) {
    return Team.builder()
        .email(teamRegistRequestDto.getEmail())
        .name(teamRegistRequestDto.getName())
        .sido(teamRegistRequestDto.getSido())
        .sigungu(teamRegistRequestDto.getSigungu())
        .description(teamRegistRequestDto.getDescription())
        .foundDate(teamRegistRequestDto.getFoundDate())
        .logo(logo)
        .build();
  }
}
