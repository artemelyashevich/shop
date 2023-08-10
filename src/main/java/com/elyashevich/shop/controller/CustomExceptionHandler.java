package com.elyashevich.shop.controller;

import com.elyashevich.shop.exception.*;
import com.elyashevich.shop.utils.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(
            {
                    UserNotFoundException.class,
                    CategoryNotFoundException.class,
                    CommentNotFoundException.class,
                    ProductNotFoundException.class
            }
    )
    @ResponseBody
    public ErrorResponse handleException(RuntimeException exception) {
        return handleCustomException(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ResourceAlreadyExistException.class)
    @ResponseBody
    public ErrorResponse handleException(ResourceAlreadyExistException exception) {
        return handleCustomException(exception.getMessage());
    }

    @NotNull
    private static ErrorResponse handleCustomException(String exception) {
        log.error(exception);
        return new ErrorResponse(exception);
    }
}
