package com.myproject.service;

import com.myproject.dto.SalaryIncrementsDTO;

import java.util.List;

public interface SalaryIncrementsService {

    List<SalaryIncrementsDTO> getAll();

    SalaryIncrementsDTO getById(Integer id);

    List<SalaryIncrementsDTO> getByLeaderId(Integer leaderId);

    SalaryIncrementsDTO create(SalaryIncrementsDTO salaryIncrementsDTO);

    SalaryIncrementsDTO updateByManager(Integer id , SalaryIncrementsDTO salaryIncrementsDTO);

    SalaryIncrementsDTO updateByLeader(Integer id, SalaryIncrementsDTO salaryIncrementsDTO);

    SalaryIncrementsDTO submitToLeader(Integer id, SalaryIncrementsDTO salaryIncrementsDTO);

    void deleteById(Integer id);
}
