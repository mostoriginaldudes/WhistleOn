package io.hala.whistleon.controller.dto;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QnaListDto {

  private final long qnaId;
  private final String title;
  private final String author;
  private final LocalDate date;

  public static QnaListDto of(long qnaId, String title, String author, LocalDate date) {
    return QnaListDto.builder()
        .qnaId(qnaId)
        .title(title)
        .author(author)
        .date(date)
        .build();
  }
}
