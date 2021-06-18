package io.hala.whistleon.common.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class PrincipalHelper {

  public String getName() {
    User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return principal.getUsername();
  }
}
