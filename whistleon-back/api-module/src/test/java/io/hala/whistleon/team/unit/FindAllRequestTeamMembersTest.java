package io.hala.whistleon.team.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.hala.whistleon.ObjectHelper;
import io.hala.whistleon.controller.dto.RequestTeamMembersResponseDto;
import io.hala.whistleon.domain.team.Team;
import io.hala.whistleon.domain.team.TeamMemberRequest;
import io.hala.whistleon.domain.team.TeamMemberRequestRepository;
import io.hala.whistleon.domain.team.TeamRepository;
import io.hala.whistleon.domain.user.Role;
import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.exception.CustomException;
import io.hala.whistleon.service.PrincipalHelper;
import io.hala.whistleon.service.team.TeamMemberRequestServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FindAllRequestTeamMembersTest {

  @InjectMocks
  private TeamMemberRequestServiceImpl teamMemberRequestService;

  @Mock
  private PrincipalHelper principalHelper;
  @Mock
  private TeamRepository teamRepository;
  @Mock
  private TeamMemberRequestRepository teamMemberRequestRepository;

  private Team team;
  private List<TeamMemberRequest> teamMemberRequests = new ArrayList<>();

  @BeforeEach
  void setDummyData() {
    User user1 = ObjectHelper.getFakeUser();
    User user2 = ObjectHelper.getFakeUser();
    User user3 = ObjectHelper.getFakeUser();
    team = Team.builder().teamId(1L).email("test@test.com").name("test-team").build();

    teamMemberRequests = List.of(
        TeamMemberRequest.builder().id(1L).user(user1).team(team).build(),
        TeamMemberRequest.builder().id(2L).user(user2).team(team).build(),
        TeamMemberRequest.builder().id(3L).user(user3).team(team).build()
    );
  }

  @DisplayName("속해있는 팀이 없다면, 유저 목록을 볼 수 없다.")
  @Test
  void NotHaveTeamShouldThrowException() {
    User user = User.builder().userId(4L).name("test4").role(Role.NONE).build();
    BDDMockito.given(principalHelper.getLoginUser()).willReturn(user);

    assertThatThrownBy(() -> teamMemberRequestService.findRequestTeamMembers(1L))
        .isInstanceOf(CustomException.class);

  }

  @DisplayName("권한이 없다면, 유저 목록을 볼 수 없다.")
  @Test
  void UnauthorizedUserShouldThrowException() {
    User user = User.builder().userId(4L).name("test4").team(team).role(Role.MEMBER).build();
    BDDMockito.given(principalHelper.getLoginUser()).willReturn(user);

    assertThatThrownBy(() -> teamMemberRequestService.findRequestTeamMembers(1L))
        .isInstanceOf(CustomException.class);
  }

  @DisplayName("팀도 가지고 있고, 권한이 있다면 유저 목록을 확인할 수 있다.")
  @Test
  void TeamManagerShouldFindList() {
    User user = User.builder().userId(4L).name("test4").team(team).role(Role.LEADER).build();
    BDDMockito.given(principalHelper.getLoginUser()).willReturn(user);
    BDDMockito.given(teamMemberRequestRepository.findAllByTeam(team))
        .willReturn(teamMemberRequests);

    RequestTeamMembersResponseDto response = teamMemberRequestService.findRequestTeamMembers(1L);
    assertThat(response.getRequestUsers().size()).isEqualTo(3);
  }
}
