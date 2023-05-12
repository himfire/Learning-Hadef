package com.learning.hadef.exception;

import com.learning.hadef.domain.value.FailureEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomException extends RuntimeException{
    private String message;
    private int code;
    private FailureEnum event;
    private String source;

    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(int code, FailureEnum event, String source) {
        this.code = code;
        this.event = event;
        this.source = source;
    }

    public CustomException(String message, int code, FailureEnum event, String source) {
        this.message = message;
        this.code = code;
        this.event = event;
        this.source = source;
    }

    public CustomException(String message, int code, String source) {
        super(message);
        this.message = message;
        this.code = code;
        this.source = source;
    }

    public CustomException(String message,Throwable cause,int code,FailureEnum event,String source) {
        super(message);
        this.message = message;
        this.code = code;
        this.event =event;
        this.source = source;
    }

    public CustomException(Throwable cause,int code,FailureEnum event,String source) {
        super(cause);
        this.code = code;
        this.event = event;
        this.source = source;
    }

    public CustomException(String message,
                           Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace,
                           int code,
                           FailureEnum event,
                           String source){
        super(message,cause,enableSuppression,writableStackTrace);
        this.message = message;
        this.code = code;
        this.event = event;
        this.source = source;
    }
}
