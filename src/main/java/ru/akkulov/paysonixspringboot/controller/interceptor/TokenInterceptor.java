package ru.akkulov.paysonixspringboot.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.akkulov.paysonixspringboot.exception.TokenIncorrectHeaderException;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

  @Value(("${TOKEN}"))
  private String tokenFromConfig;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

    log.info("preHandleMethod is executed");

    String token = request.getHeader("x-token");

    if (token == null) {
      log.info("Token value is null");
      throw new TokenIncorrectHeaderException("Token is null!");
    }
    if (token.equals(tokenFromConfig)) {
      log.info("Token value is correct, token={}", token);
      return true;
    } else {
      log.info("Token value is not valid");
      throw new TokenIncorrectHeaderException("Token is not valid!");
    }

  }
}
