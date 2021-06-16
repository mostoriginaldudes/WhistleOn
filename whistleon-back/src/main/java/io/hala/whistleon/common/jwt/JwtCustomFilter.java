package io.hala.whistleon.common.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

@RequiredArgsConstructor
public class JwtCustomFilter extends GenericFilterBean {

  private static final String AUTHORIZATION = "Authorization";
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String jwtToken = ((HttpServletRequest) request).getHeader(AUTHORIZATION);

    if (StringUtils.hasText(jwtToken) && jwtTokenProvider.isValidToken(jwtToken)) {
      Authentication authentication = jwtTokenProvider.getAuthentication(jwtToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    chain.doFilter(request, response);
  }
}
