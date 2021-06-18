package io.hala.whistleon.controller;

import io.hala.whistleon.controller.dto.SignupRequestDto;
import io.hala.whistleon.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

  private final UserService userService;

  @ApiOperation("회원가입하기")
  @PostMapping
  public ResponseEntity<?> registUser(@RequestBody @Valid SignupRequestDto signupRequestDto) {
    userService.signUp(signupRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @ApiOperation("닉네임 중복 여부 확인")
  @GetMapping("/nickname/{nickname}")
  public ResponseEntity<?> checkNickname(@PathVariable String nickname) {
    userService.checkExistNickname(nickname);
    return ResponseEntity.ok(null);
  }
}
