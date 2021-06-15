package io.hala.whistleon.domain.qna;


import static org.assertj.core.api.Assertions.assertThat;

import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.domain.user.UserRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(properties = {"spring.config.location=classpath:application-dev.properties"})
public class QnaTest {

  @Autowired
  UserRepository userRepository;

  @Autowired
  QnaRepository qnaRepository;

  @Autowired
  QnaReplyRepository qnaReplyRepository;

  @Transactional
  @Test
  void qnaInsert() throws Exception {
    //Given
    User user = userRepository.findById((long) 28)
        .orElseThrow(() -> new Exception());
    Qna qna = Qna.builder()
        .user(user)
        .title("휘슬온 가격 관련 문의사항입니다.")
        .content("평생 무료로 서비스를 이용할 수 있는건가요?")
        .build();
    Long qnaId = qnaRepository.save(qna).getQnaId();

    //When
    Qna findQna = qnaRepository.findById(qnaId)
        .orElseThrow(() -> new Exception());

    //Then
    assertThat(findQna).isEqualTo(qna);
    assertThat(findQna.getTitle()).isEqualTo("휘슬온 가격 관련 문의사항입니다.");
  }

  @Transactional
  @Test
  void qnaReplyTest() throws Exception {
    // Given
//        User user = userRepository.findById((long) 28)
//                .orElseThrow(() -> new Exception());
//        Qna qna = qnaRepository.findById((long) 36)
//                .orElseThrow(() -> new Exception());
//        QnaReply qnaReply = QnaReply.builder()
//                .qna(qna)
//                .content("휘슬온은 평생 무료로 사용하실 수 있습니다!")
//                .user(user)
//                .build();
//        qnaReplyRepository.save(qnaReply);

    // When
    Qna findQna = qnaRepository.findById((long) 36)
        .orElseThrow(() -> new Exception());
    List<QnaReply> findQnaReplies = findQna.getQnaReplies();

    // Then
    for (QnaReply findQnaReply : findQnaReplies) {
      assertThat(findQnaReply.getContent()).isEqualTo("휘슬온은 평생 무료로 사용하실 수 있습니다!");
    }

  }
}
