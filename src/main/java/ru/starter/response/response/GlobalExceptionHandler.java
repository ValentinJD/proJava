package ru.starter.response.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.starter.response.dto.BaseResponse;
import ru.starter.response.dto.ErrorDto;
import ru.starter.response.exception.ServiceException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleAllExceptions(ServiceException ex) {
        return new BaseResponse<>(false, new ErrorDto(ex.getErrorCode(), ex.getErrorMessage()), null);
    }
}
