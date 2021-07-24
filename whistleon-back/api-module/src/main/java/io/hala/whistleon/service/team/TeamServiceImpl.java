package io.hala.whistleon.service.team;

import io.hala.whistleon.controller.dto.RequestTeamMemberDto;
import io.hala.whistleon.controller.dto.RequestTeamMemberResponseDto;
import io.hala.whistleon.controller.dto.TeamRegistRequestDto;
import io.hala.whistleon.controller.dto.TeamUpdateRequestDto;
import io.hala.whistleon.domain.team.Team;
import io.hala.whistleon.domain.team.TeamMemberRequest;
import io.hala.whistleon.domain.team.TeamMemberRequestRepository;
import io.hala.whistleon.domain.team.TeamRepository;
import io.hala.whistleon.domain.team.TeamStat;
import io.hala.whistleon.domain.team.UpdateTeamInfo;
import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.exception.CustomException;
import io.hala.whistleon.exception.ExceptionCode;
import io.hala.whistleon.service.PrincipalHelper;
import io.hala.whistleon.util.FileUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {

  private final String TEAM_LOGO_DIR = "/team";
  private final PrincipalHelper principalHelper;
  private final TeamRepository teamRepository;
  private final TeamMemberRequestRepository teamMemberRequestRepository;
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

  @Transactional
  @Override
  public void updateTeam(String email, TeamUpdateRequestDto teamUpdateRequestDto) {
    User loginUser = principalHelper.getLoginUser();
    Team team = this.getTeamByEmail(email);
    if (loginUser.hasTeamAuth(team)) {
      String logo = fileUtil.uploadFile(teamUpdateRequestDto.getLogo(), TEAM_LOGO_DIR);

      team.updateTeamInfo(createTeamInfo(teamUpdateRequestDto, logo));
    }
  }

  @Transactional
  @Override
  public void registTeamMember(Long teamId) {
    User loginUser = principalHelper.getLoginUser();
    if (loginUser.hasNotTeam()) {
      Team team = getTeamById(teamId);
      checkAlreadyExistRequest(loginUser, team);
      TeamMemberRequest teamMemberRequest = createTeamMemberRequest(loginUser, team);
      teamMemberRequestRepository.save(teamMemberRequest);
    }
  }

  @Transactional
  @Override
  public RequestTeamMemberResponseDto findRequestTeamMembers(Long teamId) {
    User loginUser = principalHelper.getLoginUser();
    Team userTeam = loginUser.getTeam();
    checkValidTeam(userTeam, teamId);

    List<TeamMemberRequest> teamMemberRequests = new ArrayList<>();
    if (loginUser.hasTeamAuth(userTeam)) {
      teamMemberRequests = teamMemberRequestRepository.findAllByTeam(userTeam);
    }

    return this.createRequestTeamMemberResponse(teamMemberRequests);
  }

  /**
   * 이 아래로는 private method
   */

  private void checkAlreadyExistRequest(User user, Team team) {
    TeamMemberRequest data = teamMemberRequestRepository.findByUserAndTeam(user, team)
        .orElse(null);
    if (data != null) {
      throw new CustomException(ExceptionCode.DUPLICATE_TEAM_MEMBER_REQUEST);
    }
  }

  private void checkValidTeam(Team team, Long teamId) {
    if (team == null) {
      throw new CustomException(ExceptionCode.HAS_NOT_TEAM);
    }
    if (!team.getTeamId().equals(teamId)) {
      throw new CustomException(ExceptionCode.RESOURCES_NOT_EXIST);
    }
  }

  private TeamMemberRequest createTeamMemberRequest(User user, Team team) {
    return TeamMemberRequest.builder()
        .user(user)
        .team(team)
        .requestDate(LocalDateTime.now())
        .build();
  }

  private Team getTeamById(Long teamId) {
    return teamRepository.findById(teamId)
        .orElseThrow(() -> new CustomException(ExceptionCode.TEAM_NOT_EXIST));
  }

  private Team getTeamByEmail(String email) {
    return teamRepository.findByEmail(email)
        .orElseThrow(() -> new CustomException(ExceptionCode.TEAM_NOT_EXIST));
  }

  private void checkExistTeamByEmail(String email) {
    Team team = teamRepository.findByEmail(email)
        .orElse(null);
    if (team != null) {
      throw new CustomException(ExceptionCode.DUPLICATE_TEAM_EMAIL);
    }
  }

  private UpdateTeamInfo createTeamInfo(TeamUpdateRequestDto teamUpdateRequestDto, String logo) {
    return UpdateTeamInfo.builder()
        .name(teamUpdateRequestDto.getName())
        .sido(teamUpdateRequestDto.getSido())
        .sigungu(teamUpdateRequestDto.getSigungu())
        .foundDate(teamUpdateRequestDto.getFoundDate())
        .description(teamUpdateRequestDto.getDescription())
        .logo(logo)
        .build();
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

  private RequestTeamMemberResponseDto createRequestTeamMemberResponse(
      List<TeamMemberRequest> teamMemberRequests) {

    List<RequestTeamMemberDto> requestUsers = teamMemberRequests.stream()
        .map(RequestTeamMemberDto::of)
        .collect(Collectors.toList());

    return RequestTeamMemberResponseDto.builder()
        .requestUsers(requestUsers)
        .build();
  }
}
