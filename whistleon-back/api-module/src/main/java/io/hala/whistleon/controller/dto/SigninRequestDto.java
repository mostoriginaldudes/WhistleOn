package io.hala.whistleon.controller.dto;


import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SigninRequestDto {

  @NotNull
  private String email;
  @NotNull
  private String password;
}
