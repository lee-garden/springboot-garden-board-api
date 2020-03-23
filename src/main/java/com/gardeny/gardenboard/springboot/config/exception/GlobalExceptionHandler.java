package com.gardeny.gardenboard.springboot.config.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity notFound(Exception e) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse("NOT_FOUND",
                        "엔터티가 존재 하지 않습니다.",
                        "",
                        "");

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SignInFailedException.class)
    public ResponseEntity signInFail(Exception e) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse("UNAUTHORIZED",
                        "이메일 또는 비밀번호가 틀렸습니다.",
                        "",
                        "");

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity handleException(Exception e) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse("Error", "Internal_error", "", "");
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
