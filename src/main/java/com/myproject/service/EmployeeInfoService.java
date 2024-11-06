package com.myproject.service;

import com.myproject.dto.EmployeeInfoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeInfoService {
    List<EmployeeInfoDTO> getAll();

    EmployeeInfoDTO getById(Integer id);

    EmployeeInfoDTO create(EmployeeInfoDTO employeeProfileDTO, MultipartFile imagePath);

    EmployeeInfoDTO update(int id, EmployeeInfoDTO employeeInfoDTO, MultipartFile image);

    void deleteById(int id);


}
