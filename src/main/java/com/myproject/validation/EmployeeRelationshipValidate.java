package com.myproject.validation;

import com.myproject.commons.exception.ApiMessageError;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.commons.exception.IllegalRequestException;
import com.myproject.constant.MessageErrorConst;
import com.myproject.constant.StoredProcedureConst.EmployeeRelationship;
import com.myproject.dto.EmployeeRelationshipDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Component
@RequiredArgsConstructor
public class EmployeeRelationshipValidate {
    private final EntityManager entityManager;
    private final CommonValidate commonValidate;

    public void existById(Integer id){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EmployeeRelationship.EXISTS_BY_ID)
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("is_exist", Integer.class, ParameterMode.OUT)
                .setParameter("p_id", id);
        query.execute();
        Integer isDuplicate = (Integer) query.getOutputParameterValue("is_exist");
        if(isDuplicate == 0){
            throw new IllegalRequestException(ErrorMessages.INVALID_VALUE, new ApiMessageError(MessageErrorConst.ID_NOT_EXIST));
        }
    }

    public void validateCreate(EmployeeRelationshipDTO employeeRelationshipDTO){
        commonValidate.checkValidIdNumber(employeeRelationshipDTO.getIdentificationNumber());
        commonValidate.checkValidDate(employeeRelationshipDTO.getDateOfBirth());
        if (checkIdNumberDuplicate(employeeRelationshipDTO.getIdentificationNumber()) == 1) {
            throw new IllegalRequestException(ErrorMessages.INVALID_VALUE, new ApiMessageError(MessageErrorConst.DUPLICATE_IDENTIFICATION_NUMBER));
        }
    }

    public void validateUpdate(EmployeeRelationshipDTO employeeRelationshipDTO){
        commonValidate.checkValidIdNumber(employeeRelationshipDTO.getIdentificationNumber());
        commonValidate.checkValidDate(employeeRelationshipDTO.getDateOfBirth());
    }

    private Integer checkIdNumberDuplicate(String idNumber){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EmployeeRelationship.CHECK_DUPLICATE_IDENTIFICATION_NUMBER)
                .registerStoredProcedureParameter("id_number", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("is_duplicate", Integer.class, ParameterMode.OUT)
                .setParameter("id_number", idNumber);
        return (Integer) query.getOutputParameterValue("is_duplicate");
    }
}
