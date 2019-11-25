package com.optum.eem.demo.api;

import static com.google.common.collect.Lists.newArrayList;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.optum.eem.demo.controller"))
        .paths(PathSelectors.any()) // PathSelectors.ant("/foos/*") or PathSelectors.regex("/.*")
        .build()
        .apiInfo(apiInfo())
        .useDefaultResponseMessages(false)
        .globalResponseMessage(
            RequestMethod.GET,
            newArrayList(
                new ResponseMessageBuilder()
                    .code(500)
                    .message("500 message")
                    .responseModel(new ModelRef("ApiError"))
                    .build(),
                new ResponseMessageBuilder().code(403).message("Forbidden!").build()));
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Eem REST API")
        .description(
            "Employee Management Swagger REST API with Prometheus Metrics and Jaeger tracing!!!")
        .contact(new Contact("Supraja Doma", "http://www.optum.com", "supraja.doma@optum.com"))
        .license("Optum 2020 :-) ")
        .licenseUrl("")
        .version("1.0.0")
        .build();
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

    registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
}
