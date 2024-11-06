package com.myproject.controller;

import com.myproject.commons.CustomResponse;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.dto.EmployeeCertificateDTO;
import com.myproject.service.EmployeeCertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/certificate")
@RequiredArgsConstructor
public class EmployeeCertificateController {
    private final EmployeeCertificateService employeeCertificateService;

    @GetMapping("/{id}")
    public CustomResponse<EmployeeCertificateDTO> getById(@PathVariable("id") Integer id) {
        EmployeeCertificateDTO result = employeeCertificateService.getById(id);
        return CustomResponse.build(result);
    }

    @GetMapping("/employee-id/{id}")
    public CustomResponse<List<EmployeeCertificateDTO>> getByEmployeeId(@PathVariable("id") Integer id) {
        return CustomResponse.build(employeeCertificateService.getByEmployeeId(id));
    }

    @PostMapping
    public CustomResponse<EmployeeCertificateDTO> create(@RequestBody EmployeeCertificateDTO employeeCertificateDTO) {
        EmployeeCertificateDTO result = employeeCertificateService.create(employeeCertificateDTO);
        return CustomResponse.build(result);
    }

    @PutMapping("/{id}")
    public CustomResponse<EmployeeCertificateDTO> update(
            @PathVariable("id")  Integer id,
            @RequestBody EmployeeCertificateDTO employeeCertificateDTO) {
        EmployeeCertificateDTO result = employeeCertificateService.update(id, employeeCertificateDTO);
        return CustomResponse.build(result);
    }

    @DeleteMapping("/{id}")
    public CustomResponse<String> delete(@PathVariable("id") int id) {
        employeeCertificateService.delete(id);
        return CustomResponse.build(ErrorMessages.SUCCESS);
    }
}
