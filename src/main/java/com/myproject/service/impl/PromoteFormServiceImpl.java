package com.myproject.service.impl;

import com.myproject.constant.Status;
import com.myproject.constant.StoredProcedureConst.PromoteForm;
import com.myproject.dto.PromoteFormDTO;
import com.myproject.service.PromoteFormService;
import com.myproject.utils.JsonUtils;
import com.myproject.validation.PromoteFormValidate;
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
public class PromoteFormServiceImpl implements PromoteFormService {
    private final EntityManager entityManager;
    private final PromoteFormValidate promoteFormValidate;

    @Override
    @SuppressWarnings("unchecked")
    public List<PromoteFormDTO> getAll() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(PromoteForm.GET_ALL, "PromoteFormMapper");
        return query.getResultList();
    }

    @Override
    public PromoteFormDTO getById(Integer id) {
        promoteFormValidate.existById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(PromoteForm.GET_BY_ID, "PromoteFormMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", id);
        return (PromoteFormDTO) query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PromoteFormDTO> getByLeaderId(Integer leaderId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(PromoteForm.GET_BY_LEADER_ID, "PromoteFormMapper")
                .registerStoredProcedureParameter("p_leader_id", Integer.class, ParameterMode.IN)
                .setParameter("p_leader_id", leaderId);
        return (List<PromoteFormDTO> )query.getResultList();
    }

    @Override
    public PromoteFormDTO create(PromoteFormDTO promoteFormDTO) {
        promoteFormDTO.setStatus(Status.CREATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(PromoteForm.CREATE, "PromoteFormMapper")
                .registerStoredProcedureParameter("promote_form_json", String.class, ParameterMode.IN)
                .setParameter("promote_form_json", JsonUtils.objectToJson(promoteFormDTO));
        return (PromoteFormDTO) query.getSingleResult();
    }

    @Override
    public PromoteFormDTO updateByManager(Integer id, PromoteFormDTO promoteFormDTO) {
        promoteFormValidate.existById(id);
        promoteFormDTO.setStatus(Status.UPDATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(PromoteForm.UPDATE_BY_MANAGER, "PromoteFormMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("promote_form_json", String.class, ParameterMode.IN)
                .setParameter("p_id", id)
                .setParameter("promote_form_json", JsonUtils.objectToJson(promoteFormDTO));
        return (PromoteFormDTO) query.getSingleResult();
    }

    @Override
    public PromoteFormDTO updateByLeader(Integer id, PromoteFormDTO promoteFormDTO) {
        promoteFormValidate.existById(id);
        if (promoteFormDTO.getStatus().equals(ACCEPTED.getValue())) {
            promoteFormDTO.setAcceptDate(LocalDate.now());
        }
        if (promoteFormDTO.getStatus().equals(REJECTED.getValue())) {
            promoteFormDTO.setRejectDate(LocalDate.now());
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(PromoteForm.UPDATE_BY_LEADER, "PromoteFormMapper")
                        .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter("promote_form_json", String.class, ParameterMode.IN)
                        .setParameter("p_id", id)
                        .setParameter("promote_form_json", JsonUtils.objectToJson(promoteFormDTO));
        return (PromoteFormDTO) query.getSingleResult();
    }

    @Override
    public PromoteFormDTO submitToLeader(Integer id, PromoteFormDTO promoteFormDTO) {
        promoteFormValidate.existById(id);
        promoteFormDTO.setStatus(PENDING.getValue());
        promoteFormDTO.setSubmissionDate(LocalDate.now());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(PromoteForm.SUBMIT, "PromoteFormMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("promote_form_json", String.class, ParameterMode.IN)
                .setParameter("p_id", id)
                .setParameter("promote_form_json", JsonUtils.objectToJson(promoteFormDTO));
        return (PromoteFormDTO) query.getSingleResult();
    }

    @Override
    public void deleteById(Integer id) {
        promoteFormValidate.existById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(PromoteForm.DELETE_BY_ID, "PromoteFormMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", id);
        query.execute();
    }
}
