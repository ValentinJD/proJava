package ru.java.pro.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.java.pro.dto.BaseResponse;
import ru.java.pro.dto.ErrorDto;
import ru.java.pro.dto.OrderDto;
import ru.java.pro.exception.ServiceException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<OrderDto> handleAllExceptions(ServiceException ex) {
        return new BaseResponse<>(false, new ErrorDto(ex.getErrorCode(), ex.getErrorMessage()), null);
    }
}
