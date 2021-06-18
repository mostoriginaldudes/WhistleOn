package io.hala.whistleon.controller;

import io.hala.whistleon.controller.dto.CheckUserRequestDto;
import io.hala.whistleon.controller.dto.SignupRequestDto;
import io.hala.whistleon.controller.dto.UpdateUserInfoRequestDto;
import io.hala.whistleon.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

  @ApiOperation("마이페이지 조회")
  @GetMapping("/email/{email}")
  public ResponseEntity<?> findUserInfo(@PathVariable String email) {

    return ResponseEntity.ok(userService.getUserInfo(email));
  }

  @ApiOperation("회원정보 수정 전 암호 입력")
  @PostMapping("/checkInfo")
  public ResponseEntity<?> checkInfo(@RequestBody CheckUserRequestDto checkUserRequestDto) {
    userService.checkUserInfo(checkUserRequestDto);
    return ResponseEntity.ok(null);
  }

  @ApiOperation("회원정보 수정 요청")
  @PatchMapping("/email/{email}")
  public ResponseEntity<?> updateUserInfo(@PathVariable String email, @RequestBody
      UpdateUserInfoRequestDto updateUserInfoRequestDto) {
    userService.updateUserInfo(email, updateUserInfoRequestDto);

    return ResponseEntity.ok(null);
  }

  @ApiOperation("회원정보 삭제 요청")
  @DeleteMapping("/email/{email}")
  public ResponseEntity<?> deleteUser(@PathVariable String email) {
    userService.deleteUser(email);

    return ResponseEntity.ok(null);
  }
}
