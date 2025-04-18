package com.hyodori.backend.exception;

public class PhoneNumberDuplicateException  extends RuntimeException{
    public PhoneNumberDuplicateException(String message) {
        super(message);
    }
}
