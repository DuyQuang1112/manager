package com.myproject.service;

import com.myproject.dto.EmployeeCertificateDTO;

import java.util.List;

public interface EmployeeCertificateService {
    EmployeeCertificateDTO create(EmployeeCertificateDTO certificateDTO);

    EmployeeCertificateDTO update(Integer id, EmployeeCertificateDTO certificateDTO);

    void delete(Integer id);

    EmployeeCertificateDTO getById(Integer id);

    List<EmployeeCertificateDTO> getByEmployeeId(Integer id);
}
