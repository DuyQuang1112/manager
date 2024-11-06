package com.myproject.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IllegalRequestException extends CustomException {
    private final ApiMessageError apiMessageError;

    public IllegalRequestException(ErrorMessage message, ApiMessageError apiMessageError) {
        super(message);
        this.apiMessageError = apiMessageError;
    }
}
