package com.myproject.controller;

import com.myproject.commons.OctResponse;
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
    public OctResponse<EmployeeCertificateDTO> getById(@PathVariable("id") Integer id) {
        EmployeeCertificateDTO result = employeeCertificateService.getById(id);
        return OctResponse.build(result);
    }

    @GetMapping("/employee-id/{id}")
    public OctResponse<List<EmployeeCertificateDTO>> getByEmployeeId(@PathVariable("id") Integer id) {
        return OctResponse.build(employeeCertificateService.getByEmployeeId(id));
    }

    @PostMapping
    public OctResponse<EmployeeCertificateDTO> create(@RequestBody EmployeeCertificateDTO employeeCertificateDTO) {
        EmployeeCertificateDTO result = employeeCertificateService.create(employeeCertificateDTO);
        return OctResponse.build(result);
    }

    @PutMapping("/{id}")
    public OctResponse<EmployeeCertificateDTO> update(
            @PathVariable("id")  Integer id,
            @RequestBody EmployeeCertificateDTO employeeCertificateDTO) {
        EmployeeCertificateDTO result = employeeCertificateService.update(id, employeeCertificateDTO);
        return OctResponse.build(result);
    }

    @DeleteMapping("/{id}")
    public OctResponse<String> delete(@PathVariable("id") int id) {
        employeeCertificateService.delete(id);
        return OctResponse.build(ErrorMessages.SUCCESS);
    }
}
