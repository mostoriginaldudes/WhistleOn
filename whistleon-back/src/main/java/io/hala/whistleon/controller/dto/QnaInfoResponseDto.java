package io.hala.whistleon.controller.dto;

import io.hala.whistleon.domain.qna.Qna;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class QnaInfoResponseDto {

  private final long qnaId;
  private final String title;
  private final String author;
  private final LocalDate date;
  private final String content;
  private final List<QnaReplyResponseDto> replies;

  public static QnaInfoResponseDto of(Qna qna, List<QnaReplyResponseDto> qnaReplies) {
    return QnaInfoResponseDto.builder()
        .qnaId(qna.getQnaId())
        .title(qna.getTitle())
        .content(qna.getContent())
        .author(qna.getUser().getNickname())
        .date(qna.getDate())
        .replies(qnaReplies)
        .build();
  }
}
