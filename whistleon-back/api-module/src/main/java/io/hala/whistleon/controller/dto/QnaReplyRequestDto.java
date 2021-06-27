package io.hala.whistleon.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.hala.whistleon.domain.qna.Qna;
import io.hala.whistleon.domain.qna.QnaReply;
import io.hala.whistleon.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QnaReplyRequestDto {

  private String content;

  public QnaReply toQnaReply(User user, Qna qna) {
    return QnaReply.builder()
        .content(content)
        .user(user)
        .qna(qna)
        .build();
  }
}
