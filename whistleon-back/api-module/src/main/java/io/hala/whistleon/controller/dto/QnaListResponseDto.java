package io.hala.whistleon.controller.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QnaListResponseDto {

  private final List<QnaListDto> qnas;
  private final boolean hasNextPage;
  private final int totalPages;
  private final int nowPage;

  public static QnaListResponseDto of(List<QnaListDto> qnas, int page, boolean hasNextPage,
      int totalPages) {
    return QnaListResponseDto.builder()
        .qnas(qnas)
        .nowPage(page)
        .hasNextPage(hasNextPage)
        .totalPages(totalPages)
        .build();
  }

}
