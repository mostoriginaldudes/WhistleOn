package io.hala.whistleon.domain.match;

import static org.assertj.core.api.Assertions.assertThat;

import io.hala.whistleon.domain.team.Team;
import io.hala.whistleon.domain.team.TeamRepository;
import io.hala.whistleon.domain.user.UserRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(properties = {"spring.config.location=classpath:application-dev.properties"})
public class MatchTest {

  @Autowired
  private MatchRepository matchRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private TeamMatchRepository teamMatchRepository;

  @Test
  @Transactional
  void insertTest() throws Exception {
    Match match = Match.builder()
        .kickoffTime(LocalDateTime.now())
        .location("인천광역시 연수구 청학동")
        .stadium("용담근린공원")
        .build();

    matchRepository.save(match);

    Team homeTeam = teamRepository.findById((long) 41).orElseThrow(() -> new Exception());
    TeamMatch teamMatch = TeamMatch.builder()
        .match(match)
        .team(homeTeam)
        .teamStatus(TeamStatus.HOME)
        .build();

    Match findMatch = teamMatchRepository.save(teamMatch).getMatch();
    assertThat(findMatch).isEqualTo(match);
  }
}
