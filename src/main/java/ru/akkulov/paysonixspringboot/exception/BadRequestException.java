package ru.akkulov.paysonixspringboot.exception;

public class BadRequestException extends GeneralException {

    public BadRequestException(String msg) {
        super(msg);
    }
}
