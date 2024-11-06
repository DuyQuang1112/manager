package com.myproject.service.impl;

import com.myproject.commons.exception.ApiMessageError;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.commons.exception.OctIllegalRequestException;
import com.myproject.constant.StoredProcedureConst.EmployeeInfo;
import com.myproject.dto.EmployeeInfoDTO;
import com.myproject.service.EmployeeInfoService;
import com.myproject.utils.Upload;
import com.myproject.validation.EmployeeInfoValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeInfoServiceImpl implements EmployeeInfoService {

    private final EntityManager entityManager;
    private final EmployeeInfoValidation employeeInfoValidation;

    @Override
    @SuppressWarnings("unchecked")
    public List<EmployeeInfoDTO> getAll() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EmployeeInfo.GET_ALL, "EmployeeInfoMapper");
        return (List<EmployeeInfoDTO>) query.getResultList();
    }

    @Override
    public EmployeeInfoDTO getById(Integer id) {
        employeeInfoValidation.existById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EmployeeInfo.GET_BY_ID, "EmployeeInfoMapper")
                .registerStoredProcedureParameter("employeeId", Integer.class, ParameterMode.IN)
                .setParameter("employeeId", id);

        return (EmployeeInfoDTO) query.getSingleResult();
    }

    @Override
    public EmployeeInfoDTO create(EmployeeInfoDTO employeeInfoDTO, MultipartFile imageFile){
        employeeInfoValidation.validateCreate(employeeInfoDTO);

        if (imageFile != null && !imageFile.isEmpty()) {
            employeeInfoDTO.setImage(Upload.uploadFile(imageFile));
        } else {
            throw new OctIllegalRequestException(ErrorMessages.BAD_REQUEST, new ApiMessageError("Must contain image"));
        }

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EmployeeInfo.CREATE, "EmployeeInfoMapper")
                .registerStoredProcedureParameter("name", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("code", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("gender", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("dateOfBirth", LocalDate.class, ParameterMode.IN)
                .registerStoredProcedureParameter("address", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("team", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("image", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("idNumber", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("phoneNumber", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("email", String.class, ParameterMode.IN)
                .setParameter("name", employeeInfoDTO.getName())
                .setParameter("code", employeeInfoDTO.getCode())
                .setParameter("gender", employeeInfoDTO.getGender())
                .setParameter("dateOfBirth", employeeInfoDTO.getDateOfBirth())
                .setParameter("address", employeeInfoDTO.getAddress())
                .setParameter("team", employeeInfoDTO.getTeam())
                .setParameter("image", employeeInfoDTO.getImage())
                .setParameter("idNumber", employeeInfoDTO.getIdentificationNumber())
                .setParameter("phoneNumber", employeeInfoDTO.getPhoneNumber())
                .setParameter("email", employeeInfoDTO.getEmail());

        return (EmployeeInfoDTO) query.getSingleResult();
    }


    @Override
    public EmployeeInfoDTO update(int id, EmployeeInfoDTO employeeInfoDTO, MultipartFile imageFile) {
        employeeInfoValidation.validateUpdate(employeeInfoDTO);
        if (imageFile != null && !imageFile.isEmpty()) {
            employeeInfoDTO.setImage(Upload.uploadFile(imageFile));
        } else {
            EmployeeInfoDTO existingEmployee = getById(id);
            employeeInfoDTO.setImage(existingEmployee.getImage());
        }

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EmployeeInfo.UPDATE, "EmployeeInfoMapper")
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("name", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("code", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("gender", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("dateOfBirth", LocalDate.class, ParameterMode.IN)
                .registerStoredProcedureParameter("address", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("team", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("image", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("idNumber", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("phoneNumber", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("email", String.class, ParameterMode.IN)
                .setParameter("p_id", id)
                .setParameter("name", employeeInfoDTO.getName())
                .setParameter("code", employeeInfoDTO.getCode())
                .setParameter("gender", employeeInfoDTO.getGender())
                .setParameter("dateOfBirth", employeeInfoDTO.getDateOfBirth())
                .setParameter("address", employeeInfoDTO.getAddress())
                .setParameter("team", employeeInfoDTO.getTeam())
                .setParameter("image", employeeInfoDTO.getImage())
                .setParameter("idNumber", employeeInfoDTO.getIdentificationNumber())
                .setParameter("phoneNumber", employeeInfoDTO.getPhoneNumber())
                .setParameter("email", employeeInfoDTO.getEmail());

        return (EmployeeInfoDTO) query.getSingleResult();

    }

    @Override
    public void deleteById(int id) {
        employeeInfoValidation.existById(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(EmployeeInfo.DELETE)
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", id);
        query.execute();
    }

}
