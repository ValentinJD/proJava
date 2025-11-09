package ru.java.pro.dto;

import lombok.Data;

@Data
public class ErrorDto {
    private final String errorCode;
    private final String errorMessage;
}
