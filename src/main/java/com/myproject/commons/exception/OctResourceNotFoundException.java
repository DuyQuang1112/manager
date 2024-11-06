package com.myproject.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OctResourceNotFoundException extends OctException{
    private final ApiMessageError apiMessageError;

    public OctResourceNotFoundException(ErrorMessage message, ApiMessageError apiMessageError) {
        super(message);
        this.apiMessageError = apiMessageError;
    }
}
