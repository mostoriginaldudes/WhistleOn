package io.hala.whistleon.common.interceptor;

import io.hala.whistleon.service.login.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtInterceptor implements HandlerInterceptor{

    @Value("${jwt.header.auth}")
    private String HEADER_AUTH;

    private final JwtService jwtService;

    /**
     * JwtInterceptor is a class to handle login as Interceptor using JWT token.
     * We use preHandle method to validate the token before accessing the controller.
     * If token is not null and usable, method returns true.
     * Otherwise, send redirect to login page.
     *
     * @param request
     * @param response
     * @param handler
     * @return boolean: if token is usable, return true. Otherwise, return false.
     * @throws IOException
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        final String token = request.getHeader(HEADER_AUTH);
        if (token != null && jwtService.isUsableToken(token)) {
            return true;
        } else {
            // location path is a temporary value yet.
            response.sendRedirect("/");
            return false;
        }
    }
}
