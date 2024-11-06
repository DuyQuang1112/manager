package com.myproject.validation;

import com.myproject.commons.exception.ApiMessageError;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.commons.exception.OctResourceNotFoundException;
import com.myproject.constant.MessageErrorConst;
import com.myproject.constant.StoredProcedureConst.SalaryIncrements;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Component
@RequiredArgsConstructor
public class SalaryIncrementsValidate {
    private final EntityManager entityManager;

    public void existById(Integer id){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(SalaryIncrements.EXIST_BY_ID)
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("is_exist", Integer.class, ParameterMode.OUT)
                .setParameter("p_id", id);
        query.execute();
        Integer isDuplicate = (Integer) query.getOutputParameterValue("is_exist");
        if(isDuplicate == 0){
            throw new OctResourceNotFoundException(ErrorMessages.NOT_FOUND, new ApiMessageError(MessageErrorConst.ID_NOT_EXIST));
        }
    }
}
