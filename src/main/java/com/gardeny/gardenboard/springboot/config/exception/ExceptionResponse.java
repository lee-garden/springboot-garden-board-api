package com.gardeny.gardenboard.springboot.config.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    // HttpStatus
    private String status;
    // Http Default Message
    private String message;
    // Error Message to USER
    private String errorMessage;
    // Error Code
    private String errorCode;

    public ExceptionResponse(String status, String message, String errorCode, String errorMessage) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}