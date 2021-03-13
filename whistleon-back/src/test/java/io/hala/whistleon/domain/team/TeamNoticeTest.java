package io.hala.whistleon.domain.team;

import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.domain.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(properties = {"spring.config.location=classpath:application-dev.properties"})
public class TeamNoticeTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamNoticeRepository teamNoticeRepository;

    @Autowired
    private TeamNoticeReplyRepository teamNoticeReplyRepository;

    @Test
    @Transactional
    void insertTest() throws Exception {
        User user = userRepository.findById((long) 30).orElseThrow(() -> new Exception());
        Team team = user.getTeam();

        TeamNotice teamNotice = TeamNotice.builder()
                .team(team)
                .author(user)
                .title("아슬란 첫경기 관련 공지사항입니다.")
                .content("30분전에 도착해주세요 유니폼 지급합니다.")
                .build();
        //When
        Long teamNoticeId = teamNoticeRepository.save(teamNotice).getNoticeId();
        TeamNotice findTeamNotice = teamNoticeRepository.findById(teamNoticeId).orElseThrow(() -> new Exception());

        //Then
        assertThat(findTeamNotice).isEqualTo(teamNotice);
    }

    @Test
    @Transactional
    void insertReplyTest() throws Exception {
        //mock data
        TeamNotice teamNotice = teamNoticeRepository.findById((long) 44).orElseThrow(() -> new Exception());
        User author = userRepository.findById((long) 30).orElseThrow(() -> new Exception());
        TeamNoticeReply teamNoticeReply = TeamNoticeReply.builder()
                .teamNotice(teamNotice)
                .author(author)
                .content("네 알겠습니다!!")
                .build();

        teamNoticeReplyRepository.save(teamNoticeReply);
    }

    @Test
    @Transactional
    void findReplyTest() throws Exception {
        //mock data
        TeamNotice teamNotice = teamNoticeRepository.findById((long) 44).orElseThrow(() -> new Exception());
        List<TeamNoticeReply> teamNoticeReplies = teamNotice.getTeamNoticeReplies();
        for (TeamNoticeReply teamNoticeReply : teamNoticeReplies) {
            assertThat(teamNoticeReply.getContent()).isEqualTo("네 알겠습니다!!");
        }
    }
}
