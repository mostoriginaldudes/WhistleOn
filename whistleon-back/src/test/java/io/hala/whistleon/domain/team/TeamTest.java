package io.hala.whistleon.domain.team;


import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.domain.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(properties = {"spring.config.location=classpath:application-dev.properties"})
public class TeamTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Test
    void teamInsert() {
        // Given
        TeamStat teamStat = new TeamStat();
        Team team = new Team.TeamBuilder()
                .email("aslan@gmail.com")
                .name("ASLAN FC")
                .sido("인천광역시")
                .sigungu("연수구")
                .foundDate(LocalDate.now())
                .description("인천 유일 풋살팀!")
                .build();

        // When
        team.addTeamStat(teamStat);
        Long teamId = teamRepository.save(team).getTeamId();
        Optional<Team> findTeam = teamRepository.findById(teamId);

        // Then
        assertThat(team.getTeamStat()).isNotNull();
        assertThat(team.getTeamStat().getStatId()).isEqualTo(teamStat.getStatId());

        assertThat(findTeam.isPresent()).isEqualTo(true);
        assertThat(findTeam.get()).isEqualTo(team);
    }

    /**
     * mock data test
     */
    @Transactional
    @Test
    void userTeamTest() throws Exception {
        User user = userRepository.findById((long) 30).orElseThrow(() -> new Exception());
        Team team = teamRepository.findById((long) 41).orElseThrow(() -> new Exception());

        assertThat(user.getTeam()).isEqualTo(team);

    }
}
