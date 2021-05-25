package io.hala.whistleon.controller.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponseDto {
    private Long userId;
    private String nickname;
}
