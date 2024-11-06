package com.myproject.commons;

import com.myproject.commons.exception.ApiMessageError;
import com.myproject.commons.exception.ErrorMessage;
import com.myproject.commons.exception.CustomException;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
public class CustomResponse<T> {
    private int code;
    private String message;
    private ApiMessageError details;
    private T data;


    public static <T> CustomResponse<T> build(T data) {
        CustomResponse<T> response = new CustomResponse<>();
        response.data = data;
        response.code = 200;
        return response;
    }

    public static <T> CustomResponse<T> build(ErrorMessage errorMessage, ApiMessageError details) {
        CustomResponse<T> response = build(errorMessage);
        response.details = details;
        return response;
    }

    public static <T> CustomResponse<T> build(T data, ErrorMessage errorMessage) {
        CustomResponse<T> response = build(errorMessage);
        response.data = data;
        return response;
    }

    public static <T> CustomResponse<T> build(ErrorMessage errorMessage) {
        CustomResponse<T> response = new CustomResponse<>();
        response.code = errorMessage.getCode();
        response.message = errorMessage.getMessage();
        return response;
    }

    public static <T> CustomResponse<T> build(String message, Integer errCode) {
        CustomResponse<T> response = new CustomResponse<>();
        response.code = errCode;
        response.message = message;
        return response;
    }

    public static CustomResponse<String> buildApplicationException(CustomException exception) {
        return build(exception.getErrMsg());
    }
}
