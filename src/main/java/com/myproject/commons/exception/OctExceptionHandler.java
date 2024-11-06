package com.myproject.commons.exception;

import com.myproject.commons.OctResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class OctExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<OctResponse<?>> handleGlobalException(Exception e) {
        log.info("handleGlobalException. Message = {}", e.getMessage(), e);
        return new ResponseEntity<>(OctResponse.build(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
    }

    // Handle specific exception
    @ExceptionHandler(OctResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(OctResourceNotFoundException e) {
        log.info("handleNotFoundException. Message = {}", e.getErrMsg().getMessage(), e);
        return new ResponseEntity<>(OctResponse.build(e.getErrMsg(), e.getApiMessageError()), HttpStatus.BAD_REQUEST);
    }

    // Handle specific exception
    @ExceptionHandler(OctIllegalRequestException.class)
    public ResponseEntity<?> handleIllegalRequestException(OctIllegalRequestException e) {
        log.info("handleIllegalRequestException. Message = {}", e.getErrMsg().getMessage(), e);
        return new ResponseEntity<>(OctResponse.build(e.getErrMsg(), e.getApiMessageError()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException e) {
        log.info("handleBindException. Message = {}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), e);
        return new ResponseEntity<>(OctResponse.build(e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OctException.class)
    protected ResponseEntity<OctResponse<String>> handleOctException(OctException ex) {
        log.info("handleOctException. Msg = {}", ex.getErrMsg().getMessage(), ex);
        return buildResponseEntity(ex);
    }

    private ResponseEntity<OctResponse<String>> buildResponseEntity(OctException ex) {
        return new ResponseEntity<>(OctResponse.buildApplicationException(ex), HttpStatus.OK);
    }

}
