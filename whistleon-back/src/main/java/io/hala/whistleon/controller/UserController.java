package io.hala.whistleon.controller;

import io.hala.whistleon.controller.dto.SignupRequestDto;
import io.hala.whistleon.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    @ApiOperation("회원가입하기")
    @PostMapping
    public ResponseEntity<?> registUser(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @ApiOperation("닉네임 중복 여부 확인")
    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<?> checkNickname(@PathVariable String nickname) {
        userService.checkExistNickname(nickname);
        return ResponseEntity.ok(null);
    }
}
