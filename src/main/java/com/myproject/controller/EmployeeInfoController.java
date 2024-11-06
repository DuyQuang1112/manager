package com.myproject.controller;

import com.myproject.commons.OctResponse;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.dto.EmployeeInfoDTO;
import com.myproject.service.EmployeeInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeInfoController {

    private final EmployeeInfoService employeeService;

    @GetMapping
    public OctResponse<List<EmployeeInfoDTO>> getAll() {
        return OctResponse.build(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public OctResponse<EmployeeInfoDTO> getById(@PathVariable("id") int id) {
        return OctResponse.build(employeeService.getById(id));
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public OctResponse<EmployeeInfoDTO> create(
            @RequestPart("employeeProfile") @Valid EmployeeInfoDTO employeeInfoDTO,
            @RequestPart(value = "imageFile",required = false) MultipartFile imageFile) {
        EmployeeInfoDTO result = employeeService.create(employeeInfoDTO, imageFile);
        return OctResponse.build(result);
    }

    @PutMapping("/{id}")
    public OctResponse<EmployeeInfoDTO> update(
            @PathVariable("id") Integer id,
            @RequestPart("employeeProfile") @Valid EmployeeInfoDTO employeeInfoDTO,
            @RequestPart(value = "imageFile",required = false) MultipartFile imageFile) {
        EmployeeInfoDTO result = employeeService.update(id, employeeInfoDTO, imageFile);
        return OctResponse.build(result);
    }

    @DeleteMapping("/{id}")
    public OctResponse<String> deleteById(@PathVariable("id") int id) {
        employeeService.deleteById(id);
        return OctResponse.build(ErrorMessages.SUCCESS);
    }



}

