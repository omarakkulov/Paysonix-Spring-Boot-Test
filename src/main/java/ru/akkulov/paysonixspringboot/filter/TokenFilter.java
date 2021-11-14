package ru.akkulov.paysonixspringboot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.akkulov.paysonixspringboot.exception.TokenIncorrectHeaderException;
import ru.akkulov.paysonixspringboot.exception.objects.TokenIncorrectDataObject;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(TokenFilter.class);

//    @Value("${app.token}")
    @Value(("${TOKEN}"))
    private String tokenFromConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // compare token value from request-headers with the token value from config file
        try {
            String token = request.getHeader("x-token");

            if (token == null) {
                LOG.info("Token value is null");
                throw new TokenIncorrectHeaderException("Token is null!");
            }
            if (token.equals(tokenFromConfig)) {
                LOG.info("Token value is correct");
                filterChain.doFilter(request, response);
            } else {
                LOG.info("Token value is not valid");
                throw new TokenIncorrectHeaderException("Token is not valid!");
            }

        } catch (TokenIncorrectHeaderException exception) {
            setErrorResponse(HttpStatus.FORBIDDEN, response, exception);
        }
    }

    // method for sending FORBIDDEN 403-http code when a TokenIncorrectHeaderException is thrown
    public void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable ex) {
        response.setStatus(status.value());
        response.setContentType("application/json");

        TokenIncorrectDataObject data = new TokenIncorrectDataObject(ex.getMessage());
        try {
            String json = data.convertToJson();
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
