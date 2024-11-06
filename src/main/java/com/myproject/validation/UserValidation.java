package com.myproject.validation;

import com.myproject.commons.exception.ApiMessageError;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.commons.exception.IllegalRequestException;
import com.myproject.constant.MessageErrorConst;
import com.myproject.constant.StoredProcedureConst.User;
import com.myproject.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Component
@RequiredArgsConstructor
public class UserValidation {
    private final EntityManager entityManager;
    private final CommonValidate commonValidate;

    public void validateUserRegister(UserDTO userDto) {
        commonValidate.checkValidUsername(userDto.getUsername());
        commonValidate.checkValidPassword(userDto.getPassword());
        if(existUsername(userDto.getUsername()) == 1) {
            throw new IllegalRequestException(ErrorMessages.INVALID_VALUE , new ApiMessageError(MessageErrorConst.USERNAME_DUPLICATE));
        }
    }

    public void validateUserLogin(String username) {
        if(existUsername(username) == 0) {
            throw new IllegalRequestException(ErrorMessages.INVALID_VALUE , new ApiMessageError(MessageErrorConst.USERNAME_NOT_EXIST));
        }
    }


    private Integer existUsername(String username) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(User.EXISTS_BY_USERNAME)
                .registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("is_exist", Integer.class, ParameterMode.OUT)
                .setParameter("p_username", username);

        query.execute();
        return (Integer) query.getOutputParameterValue("is_exist");

    }

}
