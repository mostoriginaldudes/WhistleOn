package io.hala.whistleon.domain.team;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateTeamInfo {
  private String name;
  private String logo;
  private String sido;
  private String sigungu;
  private String description;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate foundDate;
}
