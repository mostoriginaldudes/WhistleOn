package io.hala.whistleon.domain.match;

import io.hala.whistleon.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(properties = {"spring.config.location=classpath:application-dev.properties"})
public class VoteTest {

    @Autowired
    private TeamMatchRepository teamMatchRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Test
    @Transactional
    void insertVoteTest() {
        List<Vote> votes = new ArrayList<>();
        List<TeamMatch> teamMatches = teamMatchRepository.findAll();
        for (TeamMatch teamMatch : teamMatches) {
            List<User> users = teamMatch.getTeam().getUsers();
            for (User user : users) {
                votes.add(Vote.builder()
                        .user(user)
                        .match(teamMatch.getMatch())
                        .build());
            }
        }
        voteRepository.saveAll(votes);
    }

    @Test
    @Transactional
    void updateVoteTest() throws Exception {
        Vote vote = voteRepository.findById((long) 77).orElseThrow(() -> new Exception());
        vote.updateVote(VoteStatus.ATTEND);
    }
}
