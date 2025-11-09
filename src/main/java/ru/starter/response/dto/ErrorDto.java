package ru.starter.response.dto;

import lombok.Data;

@Data
public class ErrorDto {
    private final String errorCode;
    private final String errorMessage;
}
