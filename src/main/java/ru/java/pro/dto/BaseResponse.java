package ru.java.pro.dto;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private final Boolean success;
    private final ErrorDto error;
    private final T body;
}
