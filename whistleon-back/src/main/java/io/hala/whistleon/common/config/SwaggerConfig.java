package io.hala.whistleon.common.config;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

  private final String version = "V1";

  @Bean
  public Docket apiV1() {
    return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .groupName(version)
        .select()
        .apis(RequestHandlerSelectors.basePackage("io.hala.whistleon.controller"))
        .paths(PathSelectors.ant("/**"))
        .build()
        .securitySchemes(Arrays.asList(apiKey()))
        .apiInfo(metaData());
  }

  private ApiInfo metaData() {
    return new ApiInfoBuilder()
        .title("WhistleOn REST API")
        .description("휘슬온 api 문서입니다")
        .version(version)
        .build();
  }

  private ApiKey apiKey() {
    return new ApiKey("JWT", "Authorization", "header");
  }
}
