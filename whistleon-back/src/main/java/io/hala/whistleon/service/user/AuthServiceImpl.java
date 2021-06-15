package io.hala.whistleon.service.user;

import io.hala.whistleon.common.exception.CustomException;
import io.hala.whistleon.common.exception.ExceptionCode;
import io.hala.whistleon.common.util.MailSendHelper;
import io.hala.whistleon.controller.dto.SigninRequestDto;
import io.hala.whistleon.domain.user.AuthCode;
import io.hala.whistleon.domain.user.AuthRepository;
import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.domain.user.UserRepository;
import io.hala.whistleon.service.login.JwtService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

  private final MailSendHelper mailSendHelper;
  private final JwtService jwtService;
  private final AuthRepository authRepository;
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
  public String signin(SigninRequestDto signinRequestDto) {
    User user = userRepository
        .findUserByEmailAndPassword(signinRequestDto.getEmail(), signinRequestDto.getPassword())
        .orElseThrow(() -> new CustomException(ExceptionCode.UNAUTHORIZED_MEMBER));
    return jwtService.createToken(user);
  }
}
