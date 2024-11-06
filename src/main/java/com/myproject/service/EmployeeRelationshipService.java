package com.myproject.service;

import com.myproject.dto.EmployeeRelationshipDTO;

import java.util.List;

public interface EmployeeRelationshipService {

    EmployeeRelationshipDTO getById(Integer id);

    List<EmployeeRelationshipDTO> getByEmployeeId(Integer id);

    EmployeeRelationshipDTO create(EmployeeRelationshipDTO employeeRelationshipDTO);

    EmployeeRelationshipDTO update(int id, EmployeeRelationshipDTO employeeRelationshipDTO);

    void deleteById(int id);
}
