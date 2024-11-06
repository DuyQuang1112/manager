package com.myproject.commons.exception;

import com.myproject.commons.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<?>> handleGlobalException(Exception e) {
        log.info("handleGlobalException. Message = {}", e.getMessage(), e);
        return new ResponseEntity<>(CustomResponse.build(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
    }

    // Handle specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.info("handleNotFoundException. Message = {}", e.getErrMsg().getMessage(), e);
        return new ResponseEntity<>(CustomResponse.build(e.getErrMsg(), e.getApiMessageError()), HttpStatus.BAD_REQUEST);
    }

    // Handle specific exception
    @ExceptionHandler(IllegalRequestException.class)
    public ResponseEntity<?> handleIllegalRequestException(IllegalRequestException e) {
        log.info("handleIllegalRequestException. Message = {}", e.getErrMsg().getMessage(), e);
        return new ResponseEntity<>(CustomResponse.build(e.getErrMsg(), e.getApiMessageError()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException e) {
        log.info("handleBindException. Message = {}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), e);
        return new ResponseEntity<>(CustomResponse.build(e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<CustomResponse<String>> handleOctException(CustomException ex) {
        log.info("handleOctException. Msg = {}", ex.getErrMsg().getMessage(), ex);
        return buildResponseEntity(ex);
    }

    private ResponseEntity<CustomResponse<String>> buildResponseEntity(CustomException ex) {
        return new ResponseEntity<>(CustomResponse.buildApplicationException(ex), HttpStatus.OK);
    }

}
