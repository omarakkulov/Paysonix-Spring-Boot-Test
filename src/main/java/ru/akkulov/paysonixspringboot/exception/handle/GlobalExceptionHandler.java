package ru.akkulov.paysonixspringboot.exception.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.akkulov.paysonixspringboot.exception.BadRequestException;
import ru.akkulov.paysonixspringboot.exception.TokenIncorrectHeaderException;
import ru.akkulov.paysonixspringboot.exception.objects.BadRequestData;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<BadRequestData> handleException(BadRequestException exception) {

    BadRequestData data = new BadRequestData(exception.getMessage());

    return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler
  public ResponseEntity<BadRequestData> handleException(TokenIncorrectHeaderException exception) {

    BadRequestData data = new BadRequestData(exception.getMessage());

    return new ResponseEntity<>(data, HttpStatus.FORBIDDEN);
  }


  @ExceptionHandler
  public ResponseEntity<BadRequestData> handleException(Exception exception) {

    BadRequestData data = new BadRequestData(exception.getMessage());

    return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
  }
}
