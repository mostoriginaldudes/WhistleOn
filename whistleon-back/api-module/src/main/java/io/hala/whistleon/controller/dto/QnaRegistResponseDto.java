package io.hala.whistleon.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QnaRegistResponseDto {

  private final long id;

  public static QnaRegistResponseDto of(long id) {
    return QnaRegistResponseDto.builder()
        .id(id)
        .build();
  }
}
