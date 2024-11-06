package com.myproject.controller;

import com.myproject.commons.OctResponse;
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
    public OctResponse<List<RegistrationFormDTO>> getAll() {
        return OctResponse.build(registrationFormService.getAll());
    }

    @GetMapping("/appointment-date")
    public OctResponse<Map<String,List<RegistrationFormDTO>>> getAllByAppointmentDate() {
        return OctResponse.build(registrationFormService.getAllByAppointmentDate());
    }

    @GetMapping("/{id}")
    public OctResponse<RegistrationFormDTO> getById(@PathVariable("id") Integer id) {
        RegistrationFormDTO result = registrationFormService.getById(id);
        return OctResponse.build(result);
    }

    @GetMapping("/leader-id/{leaderId}")
    public OctResponse<List<RegistrationFormDTO>> getAllByLeader(@PathVariable("leaderId") Integer leaderId) {
        List<RegistrationFormDTO> result = registrationFormService.getAllByLeader(leaderId);
        return OctResponse.build(result);
    }

    @PostMapping
    public OctResponse<RegistrationFormDTO> create(
            @RequestBody RegistrationFormDTO employeeRelationshipDTO) {
        RegistrationFormDTO result = registrationFormService.create(employeeRelationshipDTO);
        return OctResponse.build(result);
    }

    @PutMapping("/{id}")
    public OctResponse<RegistrationFormDTO> updateByManager(
            @PathVariable("id") Integer id,
            @RequestBody RegistrationFormDTO employeeRelationshipDTO) {
        RegistrationFormDTO result = registrationFormService.updateByManager(id, employeeRelationshipDTO);
        return OctResponse.build(result);
    }

    @PutMapping("by-leader/{id}")
    public OctResponse<RegistrationFormDTO> updateByLeader(
            @PathVariable("id") Integer id,
            @RequestBody RegistrationFormDTO employeeRelationshipDTO) {
        RegistrationFormDTO result = registrationFormService.updateByLeader(id, employeeRelationshipDTO);
        return OctResponse.build(result);
    }

    @PutMapping("/submit/{id}")
    public OctResponse<RegistrationFormDTO> submitToLeader(
            @PathVariable("id") Integer id) {
        RegistrationFormDTO result = registrationFormService.submitToLeader(id);
        return OctResponse.build(result);
    }

    @DeleteMapping("/{id}")
    public OctResponse<String> deleteById(@PathVariable("id") Integer id){
        registrationFormService.deleteById(id);
        return OctResponse.build(ErrorMessages.SUCCESS);
    }
}
