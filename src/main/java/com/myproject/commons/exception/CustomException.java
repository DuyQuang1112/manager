package com.myproject.commons.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    private final ErrorMessage errMsg;

    public CustomException(ErrorMessage errMsg) {
        super();
        this.errMsg = errMsg;
    }

    public CustomException() {
        super();
        errMsg = null;
    }

}
