package com.myproject.controller;

import com.myproject.commons.OctResponse;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.dto.SalaryIncrementsDTO;
import com.myproject.service.SalaryIncrementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/salary-increments")
@RequiredArgsConstructor
public class SalaryIncrementsController {
    private final SalaryIncrementsService salaryIncrementsService;

    @GetMapping
    public OctResponse<List<SalaryIncrementsDTO>> getAll(){
        return OctResponse.build(salaryIncrementsService.getAll());
    }

    @GetMapping("/{id}")
    public OctResponse<SalaryIncrementsDTO> getById(@PathVariable("id") Integer id){
        return OctResponse.build(salaryIncrementsService.getById(id));
    }

    @GetMapping("leader-id/{id}")
    public OctResponse<List<SalaryIncrementsDTO>> getByLeader(@PathVariable("id") Integer LeaderId){
        return OctResponse.build(salaryIncrementsService.getByLeaderId(LeaderId));
    }

    @PostMapping
    public OctResponse<SalaryIncrementsDTO> create(@RequestBody SalaryIncrementsDTO salaryIncrementsDTO){
        return OctResponse.build(salaryIncrementsService.create(salaryIncrementsDTO));
    }

    @PutMapping("/manager/{id}")
    public OctResponse<SalaryIncrementsDTO> updateByManager(@PathVariable("id") Integer id,
                                                      @RequestBody SalaryIncrementsDTO salaryIncrementsDTO){
        return OctResponse.build(salaryIncrementsService.updateByManager(id, salaryIncrementsDTO));
    }

    @PutMapping("/by-leader/{id}")
    public OctResponse<SalaryIncrementsDTO> updateByLeader(@PathVariable("id") Integer id,
                                                     @RequestBody SalaryIncrementsDTO salaryIncrementsDTO){
        return OctResponse.build(salaryIncrementsService.updateByLeader(id, salaryIncrementsDTO));
    }

    @PutMapping("/submit/{id}")
    public OctResponse<SalaryIncrementsDTO> submit(@PathVariable("id") Integer id,
                                             @RequestBody SalaryIncrementsDTO salaryIncrementsDTO){
        return OctResponse.build(salaryIncrementsService.submitToLeader(id, salaryIncrementsDTO));
    }

    @DeleteMapping("/{id}")
    public OctResponse<String> deleteById(@PathVariable("id") Integer id){
        salaryIncrementsService.deleteById(id);
        return OctResponse.build(ErrorMessages.SUCCESS);
    }
}
