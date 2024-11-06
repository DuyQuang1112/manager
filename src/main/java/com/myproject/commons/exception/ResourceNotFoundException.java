package com.myproject.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResourceNotFoundException extends CustomException {
    private final ApiMessageError apiMessageError;

    public ResourceNotFoundException(ErrorMessage message, ApiMessageError apiMessageError) {
        super(message);
        this.apiMessageError = apiMessageError;
    }
}
