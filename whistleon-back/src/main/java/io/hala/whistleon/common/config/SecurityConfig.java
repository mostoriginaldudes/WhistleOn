package io.hala.whistleon.common.config;

import io.hala.whistleon.common.jwt.JwtAccessDeniedHandler;
import io.hala.whistleon.common.jwt.JwtAuthenticationEntryPoint;
import io.hala.whistleon.common.jwt.JwtCustomFilter;
import io.hala.whistleon.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
  private final JwtTokenProvider jwtTokenProvider;


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 인증 예외 시, 인증 처리기
        .accessDeniedHandler(jwtAccessDeniedHandler) // 인가 예외 시 인증된 사용자가 있을 경우 핸들링
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 디폴트인 세션 사용을 없앰(토큰 사용)
        .and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/auth/**", "/users", "/users/nickname/**")
        .permitAll() //auth api, 회원가입, 닉네임 중복확인은 허용
        .anyRequest().authenticated() // 나머지는 인증 필요
        .and()
        .addFilterBefore(new JwtCustomFilter(jwtTokenProvider),
            UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers("/v2/api-docs", "/configuration/ui",
            "/swagger-resources/**", "/configuration/security",
            "/swagger-ui.html", "/webjars/**", "/swagger/**");
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
