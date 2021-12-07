package ru.akkulov.paysonixspringboot.controller.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.akkulov.paysonixspringboot.exception.TokenIncorrectHeaderException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    private final Logger LOG = LoggerFactory.getLogger(TokenInterceptor.class);

    @Value(("${TOKEN}"))
    private String tokenFromConfig;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String token = request.getHeader("x-token");

        if (token == null) {
            LOG.info("Token value is null");
            throw new TokenIncorrectHeaderException("Token is null!");
        }
        if (token.equals(tokenFromConfig)) {
            LOG.info("Token value is correct");
            return true;
        } else {
            LOG.info("Token value is not valid");
            throw new TokenIncorrectHeaderException("Token is not valid!");
        }

    }
}
