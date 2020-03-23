package com.gardeny.gardenboard.springboot.config.exception;

public class SignInFailedException extends RuntimeException {
    public SignInFailedException(String msg) {
        super(msg);
    }

    public SignInFailedException() {
        super();
    }
}
