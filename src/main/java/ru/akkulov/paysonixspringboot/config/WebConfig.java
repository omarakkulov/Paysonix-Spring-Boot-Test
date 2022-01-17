package ru.akkulov.paysonixspringboot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.akkulov.paysonixspringboot.controller.interceptor.TokenInterceptor;

@Configuration
@RequiredArgsConstructor
@PropertySource(value = "classpath:myApp.properties")
public class WebConfig implements WebMvcConfigurer {

  private final TokenInterceptor interceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(interceptor).addPathPatterns("/api/hmac/**");
  }

  @Bean
  public StringBuilder getStringBuilderBean() {
    return new StringBuilder();
  }
}
