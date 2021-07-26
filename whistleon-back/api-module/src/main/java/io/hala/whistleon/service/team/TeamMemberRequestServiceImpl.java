package io.hala.whistleon.service.team;

import io.hala.whistleon.controller.dto.RequestTeamMemberDto;
import io.hala.whistleon.controller.dto.RequestTeamMemberInfoDto;
import io.hala.whistleon.controller.dto.RequestTeamMembersResponseDto;
import io.hala.whistleon.domain.team.Team;
import io.hala.whistleon.domain.team.TeamMemberRequest;
import io.hala.whistleon.domain.team.TeamMemberRequestRepository;
import io.hala.whistleon.domain.team.TeamRepository;
import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.domain.user.UserRepository;
import io.hala.whistleon.exception.CustomException;
import io.hala.whistleon.exception.ExceptionCode;
import io.hala.whistleon.service.PrincipalHelper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TeamMemberRequestServiceImpl implements TeamMemberRequestService {

  private final PrincipalHelper principalHelper;
  private final UserRepository userRepository;
  private final TeamRepository teamRepository;
  private final TeamMemberRequestRepository teamMemberRequestRepository;

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
  public RequestTeamMembersResponseDto findRequestTeamMembers(Long teamId) {
    User loginUser = principalHelper.getLoginUser();
    Team userTeam = loginUser.getTeam();
    checkValidTeam(userTeam, teamId);

    List<TeamMemberRequest> teamMemberRequests = new ArrayList<>();
    if (loginUser.hasTeamAuth(userTeam)) {
      teamMemberRequests = teamMemberRequestRepository.findAllByTeam(userTeam);
    }

    return this.createRequestTeamMemberResponse(teamMemberRequests);
  }

  @Transactional
  @Override
  public RequestTeamMemberInfoDto findOneRequestTeamMember(Long userId, Long teamId) {
    User loginUser = principalHelper.getLoginUser();
    User requestUser = getUserById(userId);
    Team requestTeam = getTeamById(teamId);

    checkSameTeam(loginUser.getTeam(), requestTeam);
    
    RequestTeamMemberInfoDto requestTeamMemberInfoDto = null;
    if (loginUser.hasTeamAuth(requestTeam)
        && isExistTeamMemberRequest(requestUser, requestTeam)) {
      requestTeamMemberInfoDto = RequestTeamMemberInfoDto.of(requestUser);
    }
    return requestTeamMemberInfoDto;
  }

  @Transactional
  @Override
  public void approveTeamMember(Long teamId, Long userId) {
    User loginUser = principalHelper.getLoginUser();
    User requestUser = getUserById(userId);
    Team requestTeam = getTeamById(teamId);
    if (loginUser.hasTeamAuth(requestTeam)) {
      TeamMemberRequest teamMemberRequest = getTeamMemberRequest(requestUser, requestTeam);
      requestUser.joinTeam(requestTeam);
      teamMemberRequestRepository.delete(teamMemberRequest);
    }
  }

  // 아래로는 private method
  private void checkSameTeam(Team team1, Team team2) {
    if (team1 != team2) {
      throw new CustomException(ExceptionCode.BAD_REQUEST_DATA);
    }
  }

  private User getUserById(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(ExceptionCode.RESOURCES_NOT_EXIST));
  }

  private TeamMemberRequest getTeamMemberRequest(User user, Team team) {
    return teamMemberRequestRepository.findByUserAndTeam(user, team)
        .orElseThrow(() -> new CustomException(ExceptionCode.RESOURCES_NOT_EXIST));
  }

  private boolean isExistTeamMemberRequest(User user, Team team) {
    teamMemberRequestRepository.findByUserAndTeam(user, team)
        .orElseThrow(() -> new CustomException(ExceptionCode.RESOURCES_NOT_EXIST));

    return true;
  }

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


  private RequestTeamMembersResponseDto createRequestTeamMemberResponse(
      List<TeamMemberRequest> teamMemberRequests) {

    List<RequestTeamMemberDto> requestUsers = teamMemberRequests.stream()
        .map(RequestTeamMemberDto::of)
        .collect(Collectors.toList());

    return RequestTeamMembersResponseDto.builder()
        .requestUsers(requestUsers)
        .build();
  }
}
