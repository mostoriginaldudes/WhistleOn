package io.hala.whistleon.controller.dto;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QnaReplyResponseDto {

  private final long replyId;
  private final String replier;
  private final LocalDate date;
  private final String content;
}
