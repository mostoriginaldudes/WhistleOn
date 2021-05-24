package io.hala.whistleon.controller;

import io.hala.whistleon.service.user.AuthService;
import io.hala.whistleon.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
