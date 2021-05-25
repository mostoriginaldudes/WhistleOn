package io.hala.whistleon.controller;

import io.hala.whistleon.controller.dto.LoginResponseDto;
import io.hala.whistleon.controller.dto.SigninRequestDto;
import io.hala.whistleon.service.user.AuthService;
import io.hala.whistleon.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;

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
    public ResponseEntity<LoginResponseDto> signin(@RequestBody SigninRequestDto signinRequestDto, HttpServletResponse response) {
        String token = authService.signin(signinRequestDto);
        response.setHeader("Authorization", token);
        return ResponseEntity.ok(userService.getLoginUserInfo(signinRequestDto));
    }
}
