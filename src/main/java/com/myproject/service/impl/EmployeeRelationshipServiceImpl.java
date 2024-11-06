package com.myproject.service.impl;

import com.myproject.constant.StoredProcedureConst.EmployeeRelationship;
import com.myproject.dto.EmployeeRelationshipDTO;
import com.myproject.service.EmployeeRelationshipService;
import com.myproject.validation.EmployeeInfoValidation;
import com.myproject.validation.EmployeeRelationshipValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeRelationshipServiceImpl implements EmployeeRelationshipService {

    private final EntityManager entityManager;
    private final EmployeeRelationshipValidate employeeRelationshipValidate;
    private final EmployeeInfoValidation employeeInfoValidation;

    @Override
    public EmployeeRelationshipDTO getById(Integer id) {
        employeeRelationshipValidate.existById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EmployeeRelationship.GET_BY_ID, "EmployeeRelationshipMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", id);

        return (EmployeeRelationshipDTO) query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EmployeeRelationshipDTO> getByEmployeeId(Integer id) {
        employeeInfoValidation.existById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EmployeeRelationship.GET_BY_EMPLOYEE_ID,"EmployeeRelationshipMapper")
                        .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                        .setParameter("p_id", id);
        return (List<EmployeeRelationshipDTO>) query.getResultList();
    }

    @Override
    public EmployeeRelationshipDTO create(EmployeeRelationshipDTO employeeRelationshipDTO){
        employeeRelationshipValidate.validateCreate(employeeRelationshipDTO);
        employeeInfoValidation.existById(employeeRelationshipDTO.getEmployeeId());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EmployeeRelationship.CREATE, "EmployeeRelationshipMapper")
                .registerStoredProcedureParameter("p_name", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_gender", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_date_of_birth", LocalDate.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_identification_number", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_relationship", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_address", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_employee_id", Integer.class, ParameterMode.IN)
                .setParameter("p_name", employeeRelationshipDTO.getName())
                .setParameter("p_gender", employeeRelationshipDTO.getGender())
                .setParameter("p_date_of_birth", employeeRelationshipDTO.getDateOfBirth())
                .setParameter("p_identification_number", employeeRelationshipDTO.getIdentificationNumber())
                .setParameter("p_relationship", employeeRelationshipDTO.getRelationship())
                .setParameter("p_address", employeeRelationshipDTO.getAddress())
                .setParameter("p_employee_id", employeeRelationshipDTO.getEmployeeId());

        return (EmployeeRelationshipDTO) query.getSingleResult();
    }


    @Override
    public EmployeeRelationshipDTO update(int id, EmployeeRelationshipDTO employeeRelationshipDTO) {
        employeeRelationshipValidate.validateUpdate(employeeRelationshipDTO);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EmployeeRelationship.UPDATE, "EmployeeRelationshipMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_name", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_gender", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_dateOfBirth", LocalDate.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_identification_number", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_relationship", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_address", String.class, ParameterMode.IN)
                .setParameter("p_id", id)
                .setParameter("p_name", employeeRelationshipDTO.getName())
                .setParameter("p_gender", employeeRelationshipDTO.getGender())
                .setParameter("p_dateOfBirth", employeeRelationshipDTO.getDateOfBirth())
                .setParameter("p_identification_number", employeeRelationshipDTO.getIdentificationNumber())
                .setParameter("p_relationship", employeeRelationshipDTO.getRelationship())
                .setParameter("p_address", employeeRelationshipDTO.getAddress());

        return (EmployeeRelationshipDTO) query.getSingleResult();

    }

    @Override
    public void deleteById(int id) {
        employeeRelationshipValidate.existById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EmployeeRelationship.DELETE)
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", id);
        query.execute();
    }
}
