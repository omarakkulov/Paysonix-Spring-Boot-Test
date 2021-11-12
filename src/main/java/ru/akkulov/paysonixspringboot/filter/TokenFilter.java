package ru.akkulov.paysonixspringboot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.akkulov.paysonixspringboot.exception.IncorrectTokenHeaderException;
import ru.akkulov.paysonixspringboot.exception.TokenIncorrectDataObject;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
@WebFilter("api/hmac/*")
public class TokenFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(TokenFilter.class);

    @Value("${app.token}")
    private String tokenFromConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        LOG.info("Hello from TokenFilter...");

        Enumeration<String> headerNames = request.getHeaders("token");
        // пробегаемся по значением параметров из запроса и сравниваем токен из заголовков запроса
        // со значением токена из конфиг-файла
        try {
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String token = headerNames.nextElement();
                    if (token.equals(tokenFromConfig)) {
                        filterChain.doFilter(request, response);
                    } else throw new IncorrectTokenHeaderException("Token is not valid!");
                }
            }
        } catch (IncorrectTokenHeaderException exception) {
            setErrorResponse(HttpStatus.FORBIDDEN, response, exception);
        }
    }

    // метод для отправки FORBIDDEN 403 http-код, когда пробрасывается IncorrectTokenHeaderException
    public void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable ex) {
        response.setStatus(status.value());
        response.setContentType("application/json");

        TokenIncorrectDataObject data = new TokenIncorrectDataObject(ex.getMessage());
        try {
            String json = data.convertToJson();
            System.out.println(json);
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
