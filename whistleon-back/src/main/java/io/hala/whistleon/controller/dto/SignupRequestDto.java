package io.hala.whistleon.controller.dto;

import io.hala.whistleon.domain.user.Position;
import io.hala.whistleon.domain.user.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignupRequestDto {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 2, max = 5)
    private String name;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull
    private String phoneNum;

    @NotNull
    private String sido;

    @NotNull
    private String sigungu;

    @NotNull
    private String zonecode;

    @NotNull
    private String roadAddress;

    @NotNull
    @Min(100)
    @Max(250)
    private int height;

    @NotNull
    @Min(30)
    @Max(200)
    private int weight;

    @NotNull
    @Size(min = 2, max = 20)
    private String nickname;

    @NotNull
    private Position position1;

    @NotNull
    private Position position2;

    @NotNull
    private String password;

    @NotNull
    @Size(min = 10, max = 100)
    private String description;

    public User toUser() {
        return User.builder()
                .email(this.email)
                .name(this.name)
                .nickname(this.nickname)
                .sido(this.sido)
                .sigungu(this.sigungu)
                .zonecode(this.zonecode)
                .roadAddress(this.roadAddress)
                .height(this.height)
                .weight(this.weight)
                .password(this.password)
                .birthday(this.birthday)
                .phoneNum(this.phoneNum)
                .position1(this.position1)
                .position2(this.position2)
                .description(this.description)
                .build();
    }
}
