package io.hala.whistleon.domain.notice;

import static org.assertj.core.api.Assertions.assertThat;

import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.domain.user.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(properties = {"spring.config.location=classpath:application-dev.properties"})
public class NoticeTest {

  @Autowired
  private NoticeRepository noticeRepository;

  @Autowired
  private UserRepository userRepository;

  @Transactional
  @Test
  void noticeInsertTest() throws Exception {
    //Given
    Optional<User> admin = userRepository.findById((long) 28);
    Notice notice = Notice.builder()
        .title("휘슬온 이벤트 안내입니다")
        .content("휘슬온이 3월 10일부터 무료 나눔 서비스를 진행합니다.")
        .build();
    notice.addAuthor(admin.orElseThrow(IllegalArgumentException::new));

    //When
    Long noticeId = noticeRepository.save(notice).getNoticeId();
    Optional<Notice> findNotice = noticeRepository.findById(noticeId);

    //Then
    assertThat(findNotice.isPresent()).isEqualTo(true);
    assertThat(findNotice.get().getUser()).isEqualTo(admin.get());

    assertThat(findNotice.get().getTitle()).isEqualTo("휘슬온 이벤트 안내입니다");

  }
}
