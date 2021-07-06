package io.hala.whistleon.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UpdateQnaRequestDto {
  private final String title;
  private final String content;
}
