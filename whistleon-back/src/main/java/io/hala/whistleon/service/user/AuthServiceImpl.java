package io.hala.whistleon.service.user;

import io.hala.whistleon.common.exception.CustomException;
import io.hala.whistleon.common.exception.ExceptionCode;
import io.hala.whistleon.common.jwt.JwtTokenProvider;
import io.hala.whistleon.common.util.MailSendHelper;
import io.hala.whistleon.controller.dto.LoginResponseDto;
import io.hala.whistleon.controller.dto.SigninRequestDto;
import io.hala.whistleon.controller.dto.TokenDto;
import io.hala.whistleon.domain.user.AuthCode;
import io.hala.whistleon.domain.user.AuthRepository;
import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.domain.user.UserRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

  private final MailSendHelper mailSendHelper;
  private final AuthRepository authRepository;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;

  @Transactional
  @Override
  public void authEmail(String to) {
    String code = mailSendHelper.makeRandomText();
    mailSendHelper.sendMail(to, code);
    AuthCode authCode = authRepository.findAuthCodeByEmail(to)
        .orElseGet(() -> AuthCode.builder()
            .email(to)
            .code(code)
            .build());
    authCode.updateCode(code);
    authRepository.save(authCode);
  }

  @Transactional
  @Override
  public void authCode(String email, String code) {
    if (isValidCode(email, code)) {
      long id = getCodeId(email);
      deleteCode(id);
    }
  }

  private boolean isValidCode(String email, String code) {
    AuthCode authCode = authRepository.findAuthCodeByEmail(email)
        .orElseThrow(() -> new CustomException(ExceptionCode.UNAUTHORIZED_AUTH_CODE));
    if (!code.equals(authCode.getCode())) {
      throw new CustomException(ExceptionCode.UNAUTHORIZED_AUTH_CODE);
    }
    return true;
  }

  @Override
  public long getCodeId(String email) {
    AuthCode authCode = authRepository.findAuthCodeByEmail(email)
        .orElseThrow(() -> new CustomException(ExceptionCode.UNAUTHORIZED_AUTH_CODE));
    return authCode.getId();
  }

  @Override
  public void deleteCode(long id) {
    authRepository.deleteById(id);
  }

  @Override
  public LoginResponseDto signIn(SigninRequestDto signinRequestDto) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        signinRequestDto.getEmail(), signinRequestDto.getPassword());

    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    TokenDto tokenDto = jwtTokenProvider.createToken(authentication);

    User user = userRepository.findByEmail(signinRequestDto.getEmail())
        .orElseThrow(() -> new CustomException(ExceptionCode.UNAUTHORIZED_MEMBER));

    return LoginResponseDto.builder()
        .email(user.getEmail())
        .nickname(user.getNickname())
        .token(tokenDto.getToken())
        .tokenExpires(tokenDto.getTokenExpires())
        .build();
  }
}
