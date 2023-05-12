package com.learning.hadef.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class RestTemplateCustomException extends RuntimeException{
    private final String message;
    private final int errorCode;

    public RestTemplateCustomException(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
