package io.hala.whistleon.controller.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class TeamUpdateRequestDto {

  private String name;
  private MultipartFile logo;
  private String sido;
  private String sigungu;
  private String description;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate foundDate;

}
