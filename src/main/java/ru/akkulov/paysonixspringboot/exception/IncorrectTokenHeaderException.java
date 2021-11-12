package ru.akkulov.paysonixspringboot.exception;

public class IncorrectTokenHeaderException extends RuntimeException {

    public IncorrectTokenHeaderException(String message) {
        super(message);
    }
}
