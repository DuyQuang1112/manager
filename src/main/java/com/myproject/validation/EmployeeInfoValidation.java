package com.myproject.validation;

import com.myproject.commons.exception.ApiMessageError;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.commons.exception.IllegalRequestException;
import com.myproject.commons.exception.ResourceNotFoundException;
import com.myproject.constant.MessageErrorConst;
import com.myproject.constant.StoredProcedureConst;
import com.myproject.dto.EmployeeInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Component
@RequiredArgsConstructor
public class EmployeeInfoValidation {
    private final EntityManager entityManager;
    private final CommonValidate commonValidate;

    public void existById(Integer id){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(StoredProcedureConst.EmployeeInfo.EXISTS_BY_ID)
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("is_exist", Integer.class, ParameterMode.OUT)
                .setParameter("p_id", id);
        query.execute();
        Integer isDuplicate = (Integer) query.getOutputParameterValue("is_exist");
        if(isDuplicate == 0){
            throw new ResourceNotFoundException(ErrorMessages.NOT_FOUND, new ApiMessageError(MessageErrorConst.ID_NOT_EXIST));
        }
    }

    public void validateCreate(EmployeeInfoDTO employeeInfoDTO){
        commonValidate.checkValidPhoneNumber(employeeInfoDTO.getPhoneNumber());
        commonValidate.checkValidIdNumber(employeeInfoDTO.getIdentificationNumber());
        commonValidate.checkValidDate(employeeInfoDTO.getDateOfBirth());
        if (isEmailDuplicate(employeeInfoDTO.getEmail()) == 1) {
            throw new IllegalRequestException(ErrorMessages.INVALID_VALUE, new ApiMessageError(MessageErrorConst.DUPLICATE_EMAIL));
        }
        if (isPhoneDuplicate(employeeInfoDTO.getPhoneNumber()) == 1) {
            throw new IllegalRequestException(ErrorMessages.INVALID_VALUE, new ApiMessageError(MessageErrorConst.DUPLICATE_PHONE_NUMBER));
        }
        if (isIdNumberDuplicate(employeeInfoDTO.getIdentificationNumber()) == 1) {
            throw new IllegalRequestException(ErrorMessages.INVALID_VALUE, new ApiMessageError(MessageErrorConst.DUPLICATE_IDENTIFICATION_NUMBER));
        }
    }

    public void validateUpdate(EmployeeInfoDTO employeeInfoDTO){
        commonValidate.checkValidPhoneNumber(employeeInfoDTO.getPhoneNumber());
        commonValidate.checkValidIdNumber(employeeInfoDTO.getIdentificationNumber());
        commonValidate.checkValidDate(employeeInfoDTO.getDateOfBirth());
    }

    private Integer isEmailDuplicate(String email){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(StoredProcedureConst.EmployeeInfo.CHECK_DUPLICATE_EMAIL)
                .registerStoredProcedureParameter("email", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("is_duplicate", Integer.class, ParameterMode.OUT)
                .setParameter("email", email);
        query.execute();
        return (Integer) query.getOutputParameterValue("is_duplicate");
    }

    private Integer isPhoneDuplicate(String phone){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(StoredProcedureConst.EmployeeInfo.CHECK_DUPLICATE_PHONE_NUMBER)
                .registerStoredProcedureParameter("phone_number", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("is_duplicate", Integer.class, ParameterMode.OUT)
                .setParameter("phone_number", phone);
        return (Integer) query.getOutputParameterValue("is_duplicate");
    }

    private Integer isIdNumberDuplicate(String idNumber){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(StoredProcedureConst.EmployeeInfo.CHECK_DUPLICATE_IDENTIFICATION_NUMBER)
                .registerStoredProcedureParameter("id_number", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("is_duplicate", Integer.class, ParameterMode.OUT)
                .setParameter("id_number", idNumber);
        return (Integer) query.getOutputParameterValue("is_duplicate");
    }


}
