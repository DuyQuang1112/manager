package com.myproject.controller;

import com.myproject.commons.CustomResponse;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.dto.RegistrationFormDTO;
import com.myproject.service.RegistrationFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/v1/registration-form")
@RequiredArgsConstructor
public class RegistrationFormController {
    private final RegistrationFormService registrationFormService;

    @GetMapping
    public CustomResponse<List<RegistrationFormDTO>> getAll() {
        return CustomResponse.build(registrationFormService.getAll());
    }

    @GetMapping("/appointment-date")
    public CustomResponse<Map<String,List<RegistrationFormDTO>>> getAllByAppointmentDate() {
        return CustomResponse.build(registrationFormService.getAllByAppointmentDate());
    }

    @GetMapping("/{id}")
    public CustomResponse<RegistrationFormDTO> getById(@PathVariable("id") Integer id) {
        RegistrationFormDTO result = registrationFormService.getById(id);
        return CustomResponse.build(result);
    }

    @GetMapping("/leader-id/{leaderId}")
    public CustomResponse<List<RegistrationFormDTO>> getAllByLeader(@PathVariable("leaderId") Integer leaderId) {
        List<RegistrationFormDTO> result = registrationFormService.getAllByLeader(leaderId);
        return CustomResponse.build(result);
    }

    @PostMapping
    public CustomResponse<RegistrationFormDTO> create(
            @RequestBody RegistrationFormDTO employeeRelationshipDTO) {
        RegistrationFormDTO result = registrationFormService.create(employeeRelationshipDTO);
        return CustomResponse.build(result);
    }

    @PutMapping("/{id}")
    public CustomResponse<RegistrationFormDTO> updateByManager(
            @PathVariable("id") Integer id,
            @RequestBody RegistrationFormDTO employeeRelationshipDTO) {
        RegistrationFormDTO result = registrationFormService.updateByManager(id, employeeRelationshipDTO);
        return CustomResponse.build(result);
    }

    @PutMapping("by-leader/{id}")
    public CustomResponse<RegistrationFormDTO> updateByLeader(
            @PathVariable("id") Integer id,
            @RequestBody RegistrationFormDTO employeeRelationshipDTO) {
        RegistrationFormDTO result = registrationFormService.updateByLeader(id, employeeRelationshipDTO);
        return CustomResponse.build(result);
    }

    @PutMapping("/submit/{id}")
    public CustomResponse<RegistrationFormDTO> submitToLeader(
            @PathVariable("id") Integer id) {
        RegistrationFormDTO result = registrationFormService.submitToLeader(id);
        return CustomResponse.build(result);
    }

    @DeleteMapping("/{id}")
    public CustomResponse<String> deleteById(@PathVariable("id") Integer id){
        registrationFormService.deleteById(id);
        return CustomResponse.build(ErrorMessages.SUCCESS);
    }
}
