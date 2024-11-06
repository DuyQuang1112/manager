package com.myproject.service.impl;

import com.myproject.constant.Status;
import com.myproject.constant.StoredProcedureConst.ProposeForm;
import com.myproject.dto.ProposeFormDTO;
import com.myproject.service.ProposeFormService;
import com.myproject.utils.JsonUtils;
import com.myproject.validation.ProposeFormValidate;
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
public class ProposeFormServiceImpl implements ProposeFormService {
    private final EntityManager entityManager;
    private final ProposeFormValidate proposeFormValidate;

    @Override
    @SuppressWarnings("unchecked")
    public List<ProposeFormDTO> getAll() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(ProposeForm.GET_ALL, "ProposeFormMapper");
        return query.getResultList();
    }

    @Override
    public ProposeFormDTO getById(Integer id) {
        proposeFormValidate.existById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(ProposeForm.GET_BY_ID, "ProposeFormMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", id);
        return (ProposeFormDTO) query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProposeFormDTO> getByLeaderId(Integer leaderId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(ProposeForm.GET_BY_LEADER_ID, "ProposeFormMapper")
                .registerStoredProcedureParameter("p_leader_id", Integer.class, ParameterMode.IN)
                .setParameter("p_leader_id", leaderId);
        return (List<ProposeFormDTO> )query.getResultList();
    }

    @Override
    public ProposeFormDTO create(ProposeFormDTO proposeFormDTO) {
        proposeFormDTO.setStatus(Status.CREATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(ProposeForm.CREATE, "ProposeFormMapper")
                .registerStoredProcedureParameter("propose_form_json", String.class, ParameterMode.IN)
                .setParameter("propose_form_json", JsonUtils.objectToJson(proposeFormDTO));
        return (ProposeFormDTO) query.getSingleResult();
    }

    @Override
    public ProposeFormDTO updateByManager(Integer id, ProposeFormDTO proposeFormDTO) {
        proposeFormValidate.existById(id);
        proposeFormDTO.setStatus(Status.UPDATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(ProposeForm.UPDATE_BY_MANAGER, "ProposeFormMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("propose_form_json", String.class, ParameterMode.IN)
                .setParameter("p_id", id)
                .setParameter("propose_form_json", JsonUtils.objectToJson(proposeFormDTO));
        return (ProposeFormDTO) query.getSingleResult();
    }

    @Override
    public ProposeFormDTO updateByLeader(Integer id, ProposeFormDTO proposeFormDTO) {
        proposeFormValidate.existById(id);
        if (proposeFormDTO.getStatus().equals(ACCEPTED.getValue())) {
            proposeFormDTO.setAcceptDate(LocalDate.now());
        }
        if (proposeFormDTO.getStatus().equals(REJECTED.getValue())) {
            proposeFormDTO.setRejectDate(LocalDate.now());
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(ProposeForm.UPDATE_BY_LEADER, "ProposeFormMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("propose_form_json", String.class, ParameterMode.IN)
                .setParameter("p_id", id)
                .setParameter("propose_form_json", JsonUtils.objectToJson(proposeFormDTO));
        return (ProposeFormDTO) query.getSingleResult();
    }

    @Override
    public ProposeFormDTO submitToLeader(Integer id, ProposeFormDTO proposeFormDTO) {
        proposeFormValidate.existById(id);
        proposeFormDTO.setStatus(PENDING.getValue());
        proposeFormDTO.setSubmissionDate(LocalDate.now());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(ProposeForm.SUBMIT, "ProposeFormMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("propose_form_json", String.class, ParameterMode.IN)
                .setParameter("p_id", id)
                .setParameter("propose_form_json", JsonUtils.objectToJson(proposeFormDTO));
        return (ProposeFormDTO) query.getSingleResult();
    }

    @Override
    public void deleteById(Integer id) {
        proposeFormValidate.existById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(ProposeForm.DELETE_BY_ID, "ProposeFormMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", id);
        query.execute();
    }
}
