package com.myproject.controller;

import com.myproject.commons.CustomResponse;
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
    public CustomResponse<List<EmployeeInfoDTO>> getAll() {
        return CustomResponse.build(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public CustomResponse<EmployeeInfoDTO> getById(@PathVariable("id") int id) {
        return CustomResponse.build(employeeService.getById(id));
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public CustomResponse<EmployeeInfoDTO> create(
            @RequestPart("employeeProfile") @Valid EmployeeInfoDTO employeeInfoDTO,
            @RequestPart(value = "imageFile",required = false) MultipartFile imageFile) {
        EmployeeInfoDTO result = employeeService.create(employeeInfoDTO, imageFile);
        return CustomResponse.build(result);
    }

    @PutMapping("/{id}")
    public CustomResponse<EmployeeInfoDTO> update(
            @PathVariable("id") Integer id,
            @RequestPart("employeeProfile") @Valid EmployeeInfoDTO employeeInfoDTO,
            @RequestPart(value = "imageFile",required = false) MultipartFile imageFile) {
        EmployeeInfoDTO result = employeeService.update(id, employeeInfoDTO, imageFile);
        return CustomResponse.build(result);
    }

    @DeleteMapping("/{id}")
    public CustomResponse<String> deleteById(@PathVariable("id") int id) {
        employeeService.deleteById(id);
        return CustomResponse.build(ErrorMessages.SUCCESS);
    }



}

