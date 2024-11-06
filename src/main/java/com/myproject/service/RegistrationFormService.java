package com.myproject.service;

import com.myproject.dto.RegistrationFormDTO;

import java.util.List;
import java.util.Map;

public interface RegistrationFormService {
    List<RegistrationFormDTO> getAll();

    RegistrationFormDTO getById(Integer id);

    List<RegistrationFormDTO> getAllByLeader(Integer id);

    RegistrationFormDTO create(RegistrationFormDTO registrationFormDTO);

    RegistrationFormDTO updateByManager(int id , RegistrationFormDTO registrationFormDTO);

    RegistrationFormDTO updateByLeader(int id , RegistrationFormDTO registrationFormDTO);

    RegistrationFormDTO submitToLeader(Integer id);

    void deleteById(Integer id);

    Map<String,List<RegistrationFormDTO>> getAllByAppointmentDate();

}
