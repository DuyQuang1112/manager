package com.myproject.service.impl;

import com.myproject.constant.StoredProcedureConst.EmployeeCertificate;
import com.myproject.dto.EmployeeCertificateDTO;
import com.myproject.service.EmployeeCertificateService;
import com.myproject.validation.EmployeeCertificateValidate;
import com.myproject.validation.EmployeeInfoValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeCertificateServiceImpl implements EmployeeCertificateService {

    private final EntityManager entityManager;
    private final EmployeeCertificateValidate employeeCertificateValidate;
    private final EmployeeInfoValidation employeeInfoValidation;

    @Override
    public EmployeeCertificateDTO getById(Integer id) {
        employeeCertificateValidate.existById(id);
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(EmployeeCertificate.GET_BY_ID,"EmployeeCertificateMapper")
                        .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                        .setParameter("p_id", id);
        return (EmployeeCertificateDTO) query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EmployeeCertificateDTO> getByEmployeeId(Integer id) {
        employeeInfoValidation.existById(id);
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(EmployeeCertificate.GET_BY_EMPLOYEE_ID,"EmployeeCertificateMapper")
                        .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                        .setParameter("p_id", id);
        return (List<EmployeeCertificateDTO>) query.getResultList();
    }

    @Override
    public EmployeeCertificateDTO create(EmployeeCertificateDTO certificateDTO) {
        employeeCertificateValidate.validateEmployeeCertificate(certificateDTO);
        employeeInfoValidation.existById(certificateDTO.getEmployeeId());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EmployeeCertificate.CREATE, "EmployeeCertificateMapper")
                .registerStoredProcedureParameter("p_name", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_date_of_issue", LocalDate.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_content", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_major", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_employee_id", Integer.class, ParameterMode.IN)
                .setParameter("p_name", certificateDTO.getName())
                .setParameter("p_date_of_issue", certificateDTO.getDate_of_issue())
                .setParameter("p_content", certificateDTO.getContent())
                .setParameter("p_major", certificateDTO.getMajor())
                .setParameter("p_employee_id", certificateDTO.getEmployeeId());

        return (EmployeeCertificateDTO) query.getSingleResult();
    }

    @Override
    public EmployeeCertificateDTO update(Integer id, EmployeeCertificateDTO certificateDTO) {
        employeeCertificateValidate.validateEmployeeCertificate(certificateDTO);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EmployeeCertificate.UPDATE, "EmployeeCertificateMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_name", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_date_of_issue", LocalDate.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_content", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_major", String.class, ParameterMode.IN)
                .setParameter("p_id", id)
                .setParameter("p_name", certificateDTO.getName())
                .setParameter("p_date_of_issue", certificateDTO.getDate_of_issue())
                .setParameter("p_content", certificateDTO.getContent())
                .setParameter("p_major", certificateDTO.getMajor());

        return (EmployeeCertificateDTO) query.getSingleResult();
    }

    @Override
    public void delete(Integer id) {
        employeeCertificateValidate.existById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EmployeeCertificate.DELETE)
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", id);
        query.execute();
    }

}
