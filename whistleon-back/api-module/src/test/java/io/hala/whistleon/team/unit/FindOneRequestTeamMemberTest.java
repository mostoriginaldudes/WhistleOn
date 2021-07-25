package io.hala.whistleon.team.unit;


import io.hala.whistleon.ObjectHelper;
import io.hala.whistleon.controller.dto.RequestTeamMemberInfoDto;
import io.hala.whistleon.domain.team.Team;
import io.hala.whistleon.domain.team.TeamMemberRequest;
import io.hala.whistleon.domain.team.TeamMemberRequestRepository;
import io.hala.whistleon.domain.team.TeamRepository;
import io.hala.whistleon.domain.user.Role;
import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.domain.user.UserRepository;
import io.hala.whistleon.exception.CustomException;
import io.hala.whistleon.service.PrincipalHelper;
import io.hala.whistleon.service.team.TeamMemberRequestServiceImpl;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FindOneRequestTeamMemberTest {

  @InjectMocks
  private TeamMemberRequestServiceImpl teamMemberRequestService;

  @Mock
  private UserRepository userRepository;
  @Mock
  private PrincipalHelper principalHelper;
  @Mock
  private TeamRepository teamRepository;
  @Mock
  private TeamMemberRequestRepository teamMemberRequestRepository;

  private Team team1, team2;
  private User user1, user2, loginUser;
  private List<TeamMemberRequest> teamMemberRequests;

  @BeforeEach
  void setDummyData() {
    user1 = ObjectHelper.getFakeUser();
    user2 = ObjectHelper.getFakeUser();

    team1 = Team.builder().teamId(1L).email("test@test.com").name("test-team").build();
    team2 = Team.builder().teamId(2L).email("test2@test.com").name("not-my-team").build();
  }

  @Test
  void findNotExistUserShouldThrowException() {
    loginUser = User.builder().userId(4L).name("test4").team(team2).role(Role.LEADER).build();
    BDDMockito.given(principalHelper.getLoginUser())
        .willReturn(loginUser);

    BDDMockito.given(userRepository.findById(user1.getUserId()))
        .willReturn(Optional.empty());

    Assertions.assertThatThrownBy(() -> teamMemberRequestService
        .findOneRequestTeamMember(user1.getUserId(), team1.getTeamId()))
        .isInstanceOf(CustomException.class);
  }

  @Test
  void findNotExistTeamShouldThrowException() {
    loginUser = User.builder().userId(4L).name("test4").team(team2).role(Role.LEADER).build();
    BDDMockito.given(principalHelper.getLoginUser())
        .willReturn(loginUser);

    BDDMockito.given(teamRepository.findById(team1.getTeamId()))
        .willReturn(Optional.empty());
    BDDMockito.given(userRepository.findById(user1.getUserId()))
        .willReturn(Optional.of(user1));

    Assertions.assertThatThrownBy(() -> teamMemberRequestService
        .findOneRequestTeamMember(user1.getUserId(), team1.getTeamId()))
        .isInstanceOf(CustomException.class);
  }

  @Test
  void findInvalidRequestShouldThrowException() {
    loginUser = User.builder().userId(4L).name("test4").team(team1).role(Role.LEADER).build();
    BDDMockito.given(principalHelper.getLoginUser())
        .willReturn(loginUser);

    BDDMockito.given(teamRepository.findById(team2.getTeamId()))
        .willReturn(Optional.of(team2));
    BDDMockito.given(userRepository.findById(user2.getUserId()))
        .willReturn(Optional.of(user2));

    Assertions.assertThatThrownBy(() -> teamMemberRequestService
        .findOneRequestTeamMember(user2.getUserId(), team2.getTeamId()))
        .isInstanceOf(CustomException.class);
  }

  @Test
  void UnauthorizedTeamUserShouldThrowException() {
    loginUser = User.builder().userId(4L).name("test4").team(team1).role(Role.MEMBER).build();
    BDDMockito.given(principalHelper.getLoginUser())
        .willReturn(loginUser);
    BDDMockito.given(teamRepository.findById(team1.getTeamId()))
        .willReturn(Optional.of(team1));
    BDDMockito.given(userRepository.findById(user1.getUserId()))
        .willReturn(Optional.of(user1));

    Assertions.assertThatThrownBy(() -> teamMemberRequestService
        .findOneRequestTeamMember(user1.getUserId(), team1.getTeamId()))
        .isInstanceOf(CustomException.class);
  }

  @Test
  void findNotExistTeamMemberRequestShouldThrowException() {
    loginUser = User.builder().userId(4L).name("test4").team(team1).role(Role.LEADER).build();
    BDDMockito.given(principalHelper.getLoginUser())
        .willReturn(loginUser);
    BDDMockito.given(teamRepository.findById(team1.getTeamId()))
        .willReturn(Optional.of(team1));
    BDDMockito.given(userRepository.findById(user1.getUserId()))
        .willReturn(Optional.of(user1));
    BDDMockito.given(teamMemberRequestRepository.findByUserAndTeam(user1, team1))
        .willReturn(Optional.empty());

    Assertions.assertThatThrownBy(() -> teamMemberRequestService
        .findOneRequestTeamMember(user1.getUserId(), team1.getTeamId()))
        .isInstanceOf(CustomException.class);
  }

  @Test
  void successTest() {
    loginUser = User.builder().userId(4L).name("test4").team(team1).role(Role.LEADER).build();
    BDDMockito.given(principalHelper.getLoginUser())
        .willReturn(loginUser);
    BDDMockito.given(teamRepository.findById(team1.getTeamId()))
        .willReturn(Optional.of(team1));
    BDDMockito.given(userRepository.findById(user1.getUserId()))
        .willReturn(Optional.of(user1));
    BDDMockito.given(teamMemberRequestRepository.findByUserAndTeam(user1, team1))
        .willReturn(
            Optional.of(TeamMemberRequest.builder().id(1L).user(user1).team(team1).build()));

    RequestTeamMemberInfoDto response = teamMemberRequestService
        .findOneRequestTeamMember(user1.getUserId(), team1.getTeamId());

    Assertions.assertThat(response.getUserId())
        .isEqualTo(user1.getUserId());
    Assertions.assertThat(response.getName())
        .isEqualTo(user1.getName());
  }
}
