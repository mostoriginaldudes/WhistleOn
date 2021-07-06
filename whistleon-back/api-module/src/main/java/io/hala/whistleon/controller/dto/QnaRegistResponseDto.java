package io.hala.whistleon.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QnaRegistResponseDto {

  private final long qnaId;

  public static QnaRegistResponseDto of(long qnaId) {
    return QnaRegistResponseDto.builder()
        .qnaId(qnaId)
        .build();
  }
}
