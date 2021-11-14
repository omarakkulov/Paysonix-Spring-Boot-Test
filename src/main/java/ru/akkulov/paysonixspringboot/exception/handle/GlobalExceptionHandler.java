package ru.akkulov.paysonixspringboot.exception.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.akkulov.paysonixspringboot.exception.BadRequestException;
import ru.akkulov.paysonixspringboot.exception.objects.BadRequestDataObject;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BadRequestDataObject> handleException(
            BadRequestException exception) {

        BadRequestDataObject data = new BadRequestDataObject(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<BadRequestDataObject> handleException(Exception exception) {

        BadRequestDataObject data = new BadRequestDataObject(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
