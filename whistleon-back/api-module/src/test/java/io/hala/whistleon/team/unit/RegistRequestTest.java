package io.hala.whistleon.team.unit;

import io.hala.whistleon.domain.team.Team;
import io.hala.whistleon.domain.team.TeamMemberRequest;
import io.hala.whistleon.domain.team.TeamMemberRequestRepository;
import io.hala.whistleon.domain.team.TeamRepository;
import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.domain.user.UserRepository;
import io.hala.whistleon.exception.CustomException;
import io.hala.whistleon.service.PrincipalHelper;
import io.hala.whistleon.service.team.TeamServiceImpl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class RegistRequestTest {

  private User user;
  private Team team;

  @Mock
  private PrincipalHelper principalHelper;
  @Mock
  private UserRepository userRepository;
  @Mock
  private TeamRepository teamRepository;
  @Mock
  private TeamMemberRequestRepository teamMemberRequestRepository;

  @InjectMocks
  private TeamServiceImpl teamService;


  private final String email = "test@test.com";
  private final String name = "테스트";
  private final String nickname = "테스트닉넴";

  @BeforeEach
  void beforeTest() {
    user = User.builder()
        .userId(1L)
        .email(email)
        .name(name)
        .nickname(nickname)
        .build();

    team = Team.builder()
        .teamId(1L)
        .name("휘슬온팀")
        .email("whistleon@gmail.com")
        .build();

    BDDMockito.given(principalHelper.getLoginUser())
        .willReturn(user);
  }

  @DisplayName("이미 해당하는 팀에 대한 요청이 존재하면, 팀 가입 요청 실패")
  @Test
  void alreadyRequestTargetTeamShouldThrowException() {
    BDDMockito.given(teamRepository.findById(team.getTeamId()))
        .willReturn(Optional.of(team));
    BDDMockito.given(teamMemberRequestRepository.findByUserAndTeam(user, team))
        .willReturn(Optional.of(TeamMemberRequest.builder()
            .id(1L)
            .team(team)
            .user(user)
            .requestDate(LocalDateTime.now())
            .build()));

    Assertions.assertThatThrownBy(() -> teamService.registTeamMember(team.getTeamId()))
        .isInstanceOf(CustomException.class);
  }

  @DisplayName("이미 팀을 가지고 있다면, 팀 가입 요청 실패")
  @Test
  void teamRegistRequestFailTest() {
    user.createTeam(team);

    Assertions.assertThatThrownBy(() -> teamService.registTeamMember(team.getTeamId()))
        .isInstanceOf(CustomException.class);
  }

  @DisplayName("팀을 가지고 있지 않다면, 가입 요청 성공")
  @Test
  void teamRegistRequestSuccessTest() {
    BDDMockito.given(teamRepository.findById(1L))
        .willReturn(Optional.of(team));

    Assertions.assertThatCode(() -> teamService.registTeamMember(team.getTeamId()))
        .doesNotThrowAnyException();
  }
}
