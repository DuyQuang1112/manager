package com.myproject.validation;

import com.myproject.commons.exception.ApiMessageError;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.commons.exception.ResourceNotFoundException;
import com.myproject.constant.MessageErrorConst;
import com.myproject.constant.StoredProcedureConst.EmployeeCertificate;
import com.myproject.dto.EmployeeCertificateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Component
@RequiredArgsConstructor
public class EmployeeCertificateValidate {
    private final EntityManager entityManager;
    private final CommonValidate commonValidate;

    public void validateEmployeeCertificate(EmployeeCertificateDTO employeeCertificateDTO){
        commonValidate.checkValidDate(employeeCertificateDTO.getDate_of_issue());
    }

    public void existById(Integer id){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EmployeeCertificate.EXIST_BY_ID)
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("is_exist", Integer.class, ParameterMode.OUT)
                .setParameter("p_id", id);
        query.execute();
        Integer isDuplicate = (Integer) query.getOutputParameterValue("is_exist");
        if(isDuplicate == 0){
            throw new ResourceNotFoundException(ErrorMessages.NOT_FOUND, new ApiMessageError(MessageErrorConst.ID_NOT_EXIST));
        }
    }

}
