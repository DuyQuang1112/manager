package com.myproject.commons;

import com.myproject.commons.exception.ApiMessageError;
import com.myproject.commons.exception.ErrorMessage;
import com.myproject.commons.exception.OctException;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
public class OctResponse<T> {
    private int code;
    private String message;
    private ApiMessageError details;
    private T data;


    public static <T> OctResponse<T> build(T data) {
        OctResponse<T> response = new OctResponse<>();
        response.data = data;
        response.code = 200;
        return response;
    }

    public static <T> OctResponse<T> build(ErrorMessage errorMessage, ApiMessageError details) {
        OctResponse<T> response = build(errorMessage);
        response.details = details;
        return response;
    }

    public static <T> OctResponse<T> build(T data, ErrorMessage errorMessage) {
        OctResponse<T> response = build(errorMessage);
        response.data = data;
        return response;
    }

    public static <T> OctResponse<T> build(ErrorMessage errorMessage) {
        OctResponse<T> response = new OctResponse<>();
        response.code = errorMessage.getCode();
        response.message = errorMessage.getMessage();
        return response;
    }

    public static <T> OctResponse<T> build(String message, Integer errCode) {
        OctResponse<T> response = new OctResponse<>();
        response.code = errCode;
        response.message = message;
        return response;
    }

    public static OctResponse<String> buildApplicationException(OctException exception) {
        return build(exception.getErrMsg());
    }
}
