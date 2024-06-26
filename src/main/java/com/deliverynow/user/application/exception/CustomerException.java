package com.deliverynow.user.application.exception;

public class CustomerException extends RuntimeException{


    public CustomerException() {
    }

    public CustomerException(String message) {
        super(message);
    }

    public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}
