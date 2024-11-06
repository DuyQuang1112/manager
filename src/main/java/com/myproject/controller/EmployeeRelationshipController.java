package com.myproject.controller;

import com.myproject.commons.CustomResponse;
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
    public CustomResponse<EmployeeRelationshipDTO> getById(@PathVariable("id") int id) {
        return CustomResponse.build(employeeRelationshipService.getById(id));
    }

    @GetMapping("/employee-id/{id}")
    public CustomResponse<List<EmployeeRelationshipDTO>> getByEmployeeId(@PathVariable("id") Integer id) {
        return CustomResponse.build(employeeRelationshipService.getByEmployeeId(id));
    }

    @PostMapping
    public CustomResponse<EmployeeRelationshipDTO> create(
            @RequestBody @Valid EmployeeRelationshipDTO employeeRelationshipDTO) {
        EmployeeRelationshipDTO result = employeeRelationshipService.create(employeeRelationshipDTO);
        return CustomResponse.build(result);
    }

    @PutMapping("/{id}")
    public CustomResponse<EmployeeRelationshipDTO> update(
            @PathVariable("id") Integer id,
            @RequestBody @Valid EmployeeRelationshipDTO employeeRelationshipDTO) {
        EmployeeRelationshipDTO result = employeeRelationshipService.update(id, employeeRelationshipDTO);
        return CustomResponse.build(result);
    }

    @DeleteMapping("/{id}")
    public CustomResponse<String> deleteById(@PathVariable("id") int id) {
        employeeRelationshipService.deleteById(id);
        return CustomResponse.build(ErrorMessages.SUCCESS);
    }
}
