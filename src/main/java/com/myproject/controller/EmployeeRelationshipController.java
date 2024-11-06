package com.myproject.controller;

import com.myproject.commons.OctResponse;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.dto.EmployeeRelationshipDTO;
import com.myproject.service.EmployeeRelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/relationship")
@RequiredArgsConstructor
public class EmployeeRelationshipController {

    private final EmployeeRelationshipService employeeRelationshipService;

    @GetMapping("/{id}")
    public OctResponse<EmployeeRelationshipDTO> getById(@PathVariable("id") int id) {
        return OctResponse.build(employeeRelationshipService.getById(id));
    }

    @GetMapping("/employee-id/{id}")
    public OctResponse<List<EmployeeRelationshipDTO>> getByEmployeeId(@PathVariable("id") Integer id) {
        return OctResponse.build(employeeRelationshipService.getByEmployeeId(id));
    }

    @PostMapping
    public OctResponse<EmployeeRelationshipDTO> create(
            @RequestBody @Valid EmployeeRelationshipDTO employeeRelationshipDTO) {
        EmployeeRelationshipDTO result = employeeRelationshipService.create(employeeRelationshipDTO);
        return OctResponse.build(result);
    }

    @PutMapping("/{id}")
    public OctResponse<EmployeeRelationshipDTO> update(
            @PathVariable("id") Integer id,
            @RequestBody @Valid EmployeeRelationshipDTO employeeRelationshipDTO) {
        EmployeeRelationshipDTO result = employeeRelationshipService.update(id, employeeRelationshipDTO);
        return OctResponse.build(result);
    }

    @DeleteMapping("/{id}")
    public OctResponse<String> deleteById(@PathVariable("id") int id) {
        employeeRelationshipService.deleteById(id);
        return OctResponse.build(ErrorMessages.SUCCESS);
    }
}
