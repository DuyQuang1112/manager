package com.myproject.validation;

import com.myproject.commons.exception.ApiMessageError;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.commons.exception.IllegalRequestException;
import com.myproject.constant.MessageErrorConst;
import com.myproject.constant.RegexConst;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommonValidate {

    public void checkValidPhoneNumber(String phoneNumber){
        Pattern pattern = Pattern.compile(RegexConst.PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        if(!matcher.matches()){
            throw new IllegalRequestException(ErrorMessages.INVALID_VALUE, new ApiMessageError(MessageErrorConst.INVALID_PHONE_NUMBER));
        }
    }

    public void checkValidIdNumber(String idNumber){
        Pattern pattern = Pattern.compile(RegexConst.IDENTIFICATION_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(idNumber);
        if(!matcher.matches()){
            throw new IllegalRequestException(ErrorMessages.INVALID_VALUE, new ApiMessageError(MessageErrorConst.INVALID_IDENTIFICATION_NUMBER));
        }
    }

    public void checkValidUsername(String username){
        Pattern pattern = Pattern.compile(RegexConst.USERNAME_REGEX);
        Matcher matcher = pattern.matcher(username);
        if(!matcher.matches()){
            throw new IllegalRequestException(ErrorMessages.INVALID_VALUE, new ApiMessageError(MessageErrorConst.USERNAME_INVALID));
        }
    }

    public void checkValidPassword(String password){
        Pattern pattern = Pattern.compile(RegexConst.PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches()){
            throw new IllegalRequestException(ErrorMessages.INVALID_VALUE, new ApiMessageError(MessageErrorConst.PASSWORD_INVALID));
        }
    }

    public void checkValidDate(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        if (date.isAfter(currentDate)) {
            throw new IllegalRequestException(ErrorMessages.INVALID_VALUE, new ApiMessageError(MessageErrorConst.DATE_INVALID));
        }
    }


}
