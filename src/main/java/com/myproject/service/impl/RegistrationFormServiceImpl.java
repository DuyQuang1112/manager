package com.myproject.service.impl;

import com.myproject.constant.StoredProcedureConst.RegistrationForm;
import com.myproject.dto.RegistrationFormDTO;
import com.myproject.dto.user.UserDTO;
import com.myproject.service.RegistrationFormService;
import com.myproject.service.UserService;
import com.myproject.utils.JsonUtils;
import com.myproject.validation.EmployeeInfoValidation;
import com.myproject.validation.RegistrationFormValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.myproject.constant.Status.*;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.next;

@Service
@RequiredArgsConstructor
public class RegistrationFormServiceImpl implements RegistrationFormService {

    private final EntityManager entityManager;
    private final EmployeeInfoValidation employeeInfoValidation;
    private final RegistrationFormValidate registrationFormValidate;
    private final UserService userService;

    @Override
    @SuppressWarnings("unchecked")
    public List<RegistrationFormDTO> getAll() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(RegistrationForm.GET_ALL, "RegistrationFormMapper");
        return (List<RegistrationFormDTO>) query.getResultList();
    }

    @Override
    public RegistrationFormDTO getById(Integer id) {
        registrationFormValidate.existById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(RegistrationForm.GET_BY_ID, "RegistrationFormMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", id);
        return (RegistrationFormDTO) query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RegistrationFormDTO> getAllByLeader(Integer leaderId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(RegistrationForm.GET_BY_LEADER_ID, "RegistrationFormMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", leaderId);
        return (List<RegistrationFormDTO>) query.getResultList();
    }

    @Override
    public RegistrationFormDTO create(RegistrationFormDTO registrationFormDTO) {
        employeeInfoValidation.existById(registrationFormDTO.getEmployeeId());
        registrationFormDTO.setStatus(CREATED.getValue());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = userService.getByUsername(authentication.getName());
        registrationFormDTO.setManagerId(userDTO.getId());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(RegistrationForm.CREATE, "RegistrationFormMapper")
                .registerStoredProcedureParameter("registration_json", String.class, ParameterMode.IN)
                .setParameter("registration_json", JsonUtils.objectToJson(registrationFormDTO));
        return (RegistrationFormDTO) query.getSingleResult();
    }

    @Override
    public RegistrationFormDTO updateByManager(int id, RegistrationFormDTO registrationFormDTO) {
        registrationFormDTO.setStatus(UPDATED.getValue());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(RegistrationForm.UPDATE_BY_MANAGER, "RegistrationFormMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("registration_json", String.class, ParameterMode.IN)
                .setParameter("p_id", id)
                .setParameter("registration_json", JsonUtils.objectToJson(registrationFormDTO));
        return (RegistrationFormDTO) query.getSingleResult();
    }

    @Override
    public RegistrationFormDTO updateByLeader(int id, RegistrationFormDTO registrationFormDTO) {
        if (registrationFormDTO.getStatus().equals(ACCEPTED.getValue())) {
            registrationFormDTO.setAcceptDate(LocalDate.now());
        }
        if (registrationFormDTO.getStatus().equals(REJECTED.getValue())) {
            registrationFormDTO.setRejectDate(LocalDate.now());
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(RegistrationForm.UPDATE_BY_LEADER, "RegistrationFormMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("registration_json", String.class, ParameterMode.IN)
                .setParameter("p_id", id)
                .setParameter("registration_json", JsonUtils.objectToJson(registrationFormDTO));
        return (RegistrationFormDTO) query.getSingleResult();
    }


    @Override
    public RegistrationFormDTO submitToLeader(Integer id) {
        RegistrationFormDTO registrationFormDTO = new RegistrationFormDTO();
        registrationFormDTO.setStatus(PENDING.getValue());
        registrationFormDTO.setSubmissionDate(LocalDate.now());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(RegistrationForm.SUBMIT, "RegistrationFormMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("registration_json", String.class, ParameterMode.IN)
                .setParameter("p_id", id)
                .setParameter("registration_json", JsonUtils.objectToJson(registrationFormDTO));
        return (RegistrationFormDTO) query.getSingleResult();
    }

    @Override
    public void deleteById(Integer id) {
        registrationFormValidate.existById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(RegistrationForm.DELETE_BY_ID)
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", id);
        query.execute();
    }

    @Override
    public Map<String, List<RegistrationFormDTO>> getAllByAppointmentDate() {
        List<RegistrationFormDTO> registrationForms = getAll();
        Map<String, List<RegistrationFormDTO>> statusGroups = new HashMap<>();
        LocalDate now = LocalDate.now();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate endOfWeek = LocalDate.now().with(next(SUNDAY));

        // Initialize lists for each status
        statusGroups.put("expired", new ArrayList<>());
        statusGroups.put("today", new ArrayList<>());
        statusGroups.put("tomorrow", new ArrayList<>());
        statusGroups.put("thisWeek", new ArrayList<>());
        statusGroups.put("upcoming", new ArrayList<>());

        for (RegistrationFormDTO registrationFormDTO : registrationForms) {
            LocalDate appointmentDate = registrationFormDTO.getAppointmentDate();

            if (appointmentDate.isBefore(now)) {
                statusGroups.get("expired").add(registrationFormDTO);
            } else if (appointmentDate.isEqual(now)) {
                statusGroups.get("today").add(registrationFormDTO);
            } else if (appointmentDate.isEqual(now.plusDays(1))) {
                statusGroups.get("tomorrow").add(registrationFormDTO);
            } else if (appointmentDate.isAfter(tomorrow) && appointmentDate.isBefore(endOfWeek.plusDays(1))) {
                statusGroups.get("thisWeek").add(registrationFormDTO);
            } else {
                statusGroups.get("upcoming").add(registrationFormDTO);
            }
        }
        return statusGroups;
    }
}
