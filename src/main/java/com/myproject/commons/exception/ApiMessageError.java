package com.myproject.commons.exception;

import lombok.Getter;

@Getter
public class ApiMessageError  {
    private final String errorMessage;

    public ApiMessageError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
