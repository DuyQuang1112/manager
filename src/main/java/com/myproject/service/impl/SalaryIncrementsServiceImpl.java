package com.myproject.service.impl;

import com.myproject.constant.Status;
import com.myproject.constant.StoredProcedureConst.SalaryIncrements;
import com.myproject.dto.SalaryIncrementsDTO;
import com.myproject.service.SalaryIncrementsService;
import com.myproject.utils.JsonUtils;
import com.myproject.validation.SalaryIncrementsValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.List;

import static com.myproject.constant.Status.*;

@Service
@RequiredArgsConstructor
public class SalaryIncrementsServiceImpl implements SalaryIncrementsService {
    private final EntityManager entityManager;
    private final SalaryIncrementsValidate salaryIncrementsValidate;

    @Override
    @SuppressWarnings("unchecked")
    public List<SalaryIncrementsDTO> getAll() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(SalaryIncrements.GET_ALL, "SalaryIncrementsMapper");
        return query.getResultList();
    }

    @Override
    public SalaryIncrementsDTO getById(Integer id) {
        salaryIncrementsValidate.existById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(SalaryIncrements.GET_BY_ID, "SalaryIncrementsMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", id);
        return (SalaryIncrementsDTO) query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SalaryIncrementsDTO> getByLeaderId(Integer leaderId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(SalaryIncrements.GET_BY_LEADER_ID, "SalaryIncrementsMapper")
                .registerStoredProcedureParameter("p_leader_id", Integer.class, ParameterMode.IN)
                .setParameter("p_leader_id", leaderId);
        return (List<SalaryIncrementsDTO> )query.getResultList();
    }

    @Override
    public SalaryIncrementsDTO create(SalaryIncrementsDTO salaryIncrementsDTO) {
        salaryIncrementsDTO.setStatus(Status.CREATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(SalaryIncrements.CREATE, "SalaryIncrementsMapper")
                .registerStoredProcedureParameter("salary_increments_json", String.class, ParameterMode.IN)
                .setParameter("salary_increments_json", JsonUtils.objectToJson(salaryIncrementsDTO));
        return (SalaryIncrementsDTO) query.getSingleResult();
    }

    @Override
    public SalaryIncrementsDTO updateByManager(Integer id, SalaryIncrementsDTO salaryIncrementsDTO) {
        salaryIncrementsValidate.existById(id);
        salaryIncrementsDTO.setStatus(Status.UPDATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(SalaryIncrements.UPDATE_BY_MANAGER, "SalaryIncrementsMapper")
                        .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter("salary_increments_json", String.class, ParameterMode.IN)
                        .setParameter("p_id", id)
                        .setParameter("salary_increments_json", JsonUtils.objectToJson(salaryIncrementsDTO));
        return (SalaryIncrementsDTO) query.getSingleResult();
    }

    @Override
    public SalaryIncrementsDTO updateByLeader(Integer id, SalaryIncrementsDTO salaryIncrementsDTO) {
        salaryIncrementsValidate.existById(id);
        if (salaryIncrementsDTO.getStatus().equals(ACCEPTED.getValue())) {
            salaryIncrementsDTO.setAcceptDate(LocalDate.now());
        }
        if (salaryIncrementsDTO.getStatus().equals(REJECTED.getValue())) {
            salaryIncrementsDTO.setRejectDate(LocalDate.now());
        }
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(SalaryIncrements.UPDATE_BY_LEADER, "SalaryIncrementsMapper")
                        .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter("salary_increments_json", String.class, ParameterMode.IN)
                        .setParameter("p_id", id)
                        .setParameter("salary_increments_json", JsonUtils.objectToJson(salaryIncrementsDTO));
        return (SalaryIncrementsDTO) query.getSingleResult();
    }

    @Override
    public SalaryIncrementsDTO submitToLeader(Integer id, SalaryIncrementsDTO salaryIncrementsDTO) {
        salaryIncrementsValidate.existById(id);
        salaryIncrementsDTO.setStatus(PENDING.getValue());
        salaryIncrementsDTO.setSubmissionDate(LocalDate.now());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(SalaryIncrements.SUBMIT, "SalaryIncrementsMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("salary_increments_json", String.class, ParameterMode.IN)
                .setParameter("p_id", id)
                .setParameter("salary_increments_json", JsonUtils.objectToJson(salaryIncrementsDTO));
        return (SalaryIncrementsDTO) query.getSingleResult();
    }

    @Override
    public void deleteById(Integer id) {
        salaryIncrementsValidate.existById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(SalaryIncrements.DELETE_BY_ID, "SalaryIncrementsMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", id);
        query.execute();
    }
}
