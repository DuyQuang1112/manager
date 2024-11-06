package com.myproject.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OctIllegalRequestException extends  OctException{
    private final ApiMessageError apiMessageError;

    public OctIllegalRequestException(ErrorMessage message, ApiMessageError apiMessageError) {
        super(message);
        this.apiMessageError = apiMessageError;
    }
}
