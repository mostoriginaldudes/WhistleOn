package io.hala.whistleon.common.util;

import io.hala.whistleon.common.exception.CustomException;
import io.hala.whistleon.common.exception.ExceptionCode;
import io.hala.whistleon.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PrincipalHelper {

  private final UserRepository userRepository;

  public String getName() {
    User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return principal.getUsername();
  }

  public io.hala.whistleon.domain.user.User getLoginUser() {
    String email = this.getName();
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new CustomException(ExceptionCode.UNAUTHORIZED_MEMBER));
  }
}
