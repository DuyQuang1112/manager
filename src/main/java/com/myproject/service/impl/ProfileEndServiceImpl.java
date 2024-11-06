package com.myproject.service.impl;

import com.myproject.constant.Status;
import com.myproject.constant.StoredProcedureConst.ProfileEnd;
import com.myproject.dto.ProfileEndDTO;
import com.myproject.service.ProfileEndService;
import com.myproject.utils.JsonUtils;
import com.myproject.validation.ProfileEndValidate;
import com.myproject.validation.StatusValidate;
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
public class ProfileEndServiceImpl implements ProfileEndService {
    private final EntityManager entityManager;
    private final ProfileEndValidate profileEndValidate;
    private final StatusValidate statusValidate;

    @Override
    @SuppressWarnings("unchecked")
    public List<ProfileEndDTO> getAll() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(ProfileEnd.GET_ALL, "ProfileEndMapper");
        return query.getResultList();
    }

    @Override
    public ProfileEndDTO getById(Integer id) {
        profileEndValidate.existById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(ProfileEnd.GET_BY_ID, "ProfileEndMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", id);
        return (ProfileEndDTO) query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProfileEndDTO> getByLeaderId(Integer leaderId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(ProfileEnd.GET_BY_LEADER_ID, "ProfileEndMapper")
                .registerStoredProcedureParameter("p_leader_id", Integer.class, ParameterMode.IN)
                .setParameter("p_leader_id", leaderId);
        return (List<ProfileEndDTO> )query.getResultList();
    }

    @Override
    public ProfileEndDTO create(ProfileEndDTO profileEndDTO) {
        profileEndDTO.setStatus(Status.CREATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(ProfileEnd.CREATE, "ProfileEndMapper")
                .registerStoredProcedureParameter("profile_end_json", String.class, ParameterMode.IN)
                .setParameter("profile_end_json", JsonUtils.objectToJson(profileEndDTO));
        return (ProfileEndDTO) query.getSingleResult();
    }

    @Override
    public ProfileEndDTO updateByManager(Integer id, ProfileEndDTO profileEndDTO) {
        profileEndValidate.existById(id);
        profileEndDTO.setStatus(Status.UPDATED.getValue());
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(ProfileEnd.UPDATE_BY_MANAGER, "ProfileEndMapper")
                        .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter("profile_end_json", String.class, ParameterMode.IN)
                        .setParameter("p_id", id)
                        .setParameter("profile_end_json", JsonUtils.objectToJson(profileEndDTO));
        return (ProfileEndDTO) query.getSingleResult();
    }

    @Override
    public ProfileEndDTO updateByLeader(Integer id, ProfileEndDTO profileEndDTO) {
        statusValidate.validLeaderStatus(profileEndDTO.getStatus());
        profileEndValidate.existById(id);
        if (profileEndDTO.getStatus().equals(ACCEPTED.getValue())) {
            profileEndDTO.setAcceptDate(LocalDate.now());
        }
        if (profileEndDTO.getStatus().equals(REJECTED.getValue())) {
            profileEndDTO.setRejectDate(LocalDate.now());
        }
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery(ProfileEnd.UPDATE_BY_LEADER, "ProfileEndMapper")
                        .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                        .registerStoredProcedureParameter("profile_end_json", String.class, ParameterMode.IN)
                        .setParameter("p_id", id)
                        .setParameter("profile_end_json", JsonUtils.objectToJson(profileEndDTO));
        return (ProfileEndDTO) query.getSingleResult();
    }

    @Override
    public ProfileEndDTO submitToLeader(Integer id, ProfileEndDTO profileEndDTO) {
        profileEndValidate.existById(id);
        profileEndDTO.setStatus(PENDING.getValue());
        profileEndDTO.setSubmissionDate(LocalDate.now());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(ProfileEnd.SUBMIT, "RegistrationFormMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("profile_end_json", String.class, ParameterMode.IN)
                .setParameter("p_id", id)
                .setParameter("profile_end_json", JsonUtils.objectToJson(profileEndDTO));
        return (ProfileEndDTO) query.getSingleResult();
    }

    @Override
    public void deleteById(Integer id) {
        profileEndValidate.existById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(ProfileEnd.DELETE_BY_ID, "ProfileEndMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", id);
        query.execute();
    }
}
