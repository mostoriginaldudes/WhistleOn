package io.hala.whistleon.team.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import io.hala.whistleon.ObjectHelper;
import io.hala.whistleon.domain.team.Team;
import io.hala.whistleon.domain.team.TeamMemberRequest;
import io.hala.whistleon.domain.team.TeamMemberRequestRepository;
import io.hala.whistleon.domain.team.TeamRepository;
import io.hala.whistleon.domain.user.Role;
import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.domain.user.UserRepository;
import io.hala.whistleon.exception.CustomException;
import io.hala.whistleon.service.PrincipalHelper;
import io.hala.whistleon.service.team.TeamMemberRequestService;
import io.hala.whistleon.service.team.TeamMemberRequestServiceImpl;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ApproveTeamMemberTest {

  private TeamMemberRequestService teamMemberRequestService;

  @Mock
  private UserRepository userRepository;
  @Mock
  private PrincipalHelper principalHelper;
  @Mock
  private TeamRepository teamRepository;
  @Mock
  private TeamMemberRequestRepository teamMemberRequestRepository;

  private User user1;
  private Team team1;

  @BeforeEach
  void setUp() {
    teamMemberRequestService = new TeamMemberRequestServiceImpl(principalHelper, userRepository,
        teamRepository, teamMemberRequestRepository);
    user1 = ObjectHelper.getFakeUser();
    team1 = Team.builder().teamId(1L).email("test@test.com").name("test-team").build();
  }

  @DisplayName("권한이 없다면 목록을 볼 수 없다.")
  @Test
  void UnauthorizedUserShouldThrowException() {
    User loginUser = User.builder().userId(4L).name("test4").team(team1).role(Role.NONE).build();
    given(principalHelper.getLoginUser()).willReturn(loginUser);
    given(teamRepository.findById(team1.getTeamId()))
        .willReturn(Optional.of(team1));

    assertThatThrownBy(
        () -> teamMemberRequestService.approveTeamMember(team1.getTeamId(), user1.getUserId()))
        .isInstanceOf(CustomException.class);
  }

  @DisplayName("해당하는 요청이 존재하지 않을 경우 예외를 발생시킨다.")
  @Test
  void NotExistRequestShouldThrowException() {
    User loginUser = User.builder().userId(4L).name("test4").team(team1).role(Role.LEADER).build();
    given(principalHelper.getLoginUser()).willReturn(loginUser);
    given(userRepository.findById(user1.getUserId()))
        .willReturn(Optional.of(user1));
    given(teamRepository.findById(team1.getTeamId()))
        .willReturn(Optional.of(team1));
    given(teamMemberRequestRepository.findByUserAndTeam(user1, team1))
        .willReturn(Optional.empty());

    assertThatThrownBy(
        () -> teamMemberRequestService.approveTeamMember(team1.getTeamId(), user1.getUserId()))
        .isInstanceOf(CustomException.class);
  }

  @DisplayName("해당하는 요청이 존재할 경우 가입 승인을 한다.")
  @Test
  void successTest() {
    User loginUser = User.builder().userId(4L).name("test4").team(team1).role(Role.LEADER).build();
    given(principalHelper.getLoginUser()).willReturn(loginUser);
    given(userRepository.findById(user1.getUserId()))
        .willReturn(Optional.of(user1));
    given(teamRepository.findById(team1.getTeamId()))
        .willReturn(Optional.of(team1));
    given(teamMemberRequestRepository.findByUserAndTeam(user1, team1))
        .willReturn(
            Optional.of(TeamMemberRequest.builder().id(1L).team(team1).user(user1).requestDate(
                LocalDateTime.now()).build()));

    assertThatCode(() ->
        teamMemberRequestService.approveTeamMember(team1.getTeamId(), user1.getUserId()))
        .doesNotThrowAnyException();

    assertThat(user1.getTeam()).isEqualTo(team1);
  }
}
