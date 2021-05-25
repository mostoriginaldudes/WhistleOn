package io.hala.whistleon.controller.dto;


import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SigninRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
