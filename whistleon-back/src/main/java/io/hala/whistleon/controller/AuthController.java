package io.hala.whistleon.controller;

import io.hala.whistleon.controller.dto.SigninRequestDto;
import io.hala.whistleon.controller.dto.TokenDto;
import io.hala.whistleon.service.user.AuthService;
import io.hala.whistleon.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import javax.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

  private final AuthService authService;
  private final UserService userService;

  @PostMapping("/email/{email}")
  public ResponseEntity<?> authEmail(@PathVariable @Email String email) {
    if (userService.checkEmail(email)) {
      authService.authEmail(email);
    }
    return ResponseEntity.ok(null);
  }

  @GetMapping("/email/{email}/code/{code}")
  public ResponseEntity<?> authCode(@PathVariable @Email String email, @PathVariable String code) {
    authService.authCode(email, code);
    return ResponseEntity.ok(null);
  }

  @ApiOperation("로그인")
  @PostMapping("/login")
  public ResponseEntity<TokenDto> signin(@RequestBody SigninRequestDto signinRequestDto) {
    return ResponseEntity.ok(authService.signin(signinRequestDto));
  }
}
