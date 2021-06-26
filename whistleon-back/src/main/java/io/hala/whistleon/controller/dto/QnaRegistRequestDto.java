package io.hala.whistleon.controller.dto;

import io.hala.whistleon.domain.qna.Qna;
import io.hala.whistleon.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QnaRegistRequestDto {

  private final String title;
  private final String content;

  public Qna toQna(User user) {
    return Qna.builder()
        .title(this.title)
        .content(this.content)
        .user(user)
        .build();
  }
}
