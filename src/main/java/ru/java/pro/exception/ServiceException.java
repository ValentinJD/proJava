package ru.java.pro.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    public ServiceException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
