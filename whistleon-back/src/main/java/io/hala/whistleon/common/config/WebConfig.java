package io.hala.whistleon.common.config;

import io.hala.whistleon.common.interceptor.JwtInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

  private static final String[] EXCLUDE_PATH = {};

  private final JwtInterceptor jwtInterceptor;


  /**
   * For using Interceptors, you should add Interceptors on this method.
   *
   * @param registry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(jwtInterceptor)
        .addPathPatterns("/test/**")
        .excludePathPatterns(EXCLUDE_PATH);
  }


  /**
   * this method is for preventing CORS problem.
   *
   * @param registry
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedHeaders("Authorization")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD", "OPTIONS")
        .allowedOrigins("*");
  }
}
