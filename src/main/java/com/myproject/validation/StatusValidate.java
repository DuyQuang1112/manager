package com.myproject.validation;

import com.myproject.commons.exception.ApiMessageError;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.commons.exception.IllegalRequestException;
import org.springframework.stereotype.Component;

import static com.myproject.constant.MessageErrorConst.ILLEGAL_STATUS;
import static com.myproject.constant.Status.*;

@Component
public class StatusValidate {
    public void validManagerStatus(String status){
        if(!(status.equals(PENDING.getValue()) || status.equals(CREATED.getValue()) || status.equals(UPDATED.getValue()))){
            throw new IllegalRequestException(ErrorMessages.INVALID_VALUE, new ApiMessageError(ILLEGAL_STATUS));
        }
    }

    public void validLeaderStatus(String status){
        if(!(status.equals(ACCEPTED.getValue()) || status.equals(REJECTED.getValue()) || status.equals(ADDITIONAL.getValue()))){
            throw new IllegalRequestException(ErrorMessages.INVALID_VALUE, new ApiMessageError(ILLEGAL_STATUS));
        }
    }
}
