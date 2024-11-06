package com.myproject.controller;

import com.myproject.commons.CustomResponse;
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
    public CustomResponse<List<SalaryIncrementsDTO>> getAll(){
        return CustomResponse.build(salaryIncrementsService.getAll());
    }

    @GetMapping("/{id}")
    public CustomResponse<SalaryIncrementsDTO> getById(@PathVariable("id") Integer id){
        return CustomResponse.build(salaryIncrementsService.getById(id));
    }

    @GetMapping("leader-id/{id}")
    public CustomResponse<List<SalaryIncrementsDTO>> getByLeader(@PathVariable("id") Integer LeaderId){
        return CustomResponse.build(salaryIncrementsService.getByLeaderId(LeaderId));
    }

    @PostMapping
    public CustomResponse<SalaryIncrementsDTO> create(@RequestBody SalaryIncrementsDTO salaryIncrementsDTO){
        return CustomResponse.build(salaryIncrementsService.create(salaryIncrementsDTO));
    }

    @PutMapping("/manager/{id}")
    public CustomResponse<SalaryIncrementsDTO> updateByManager(@PathVariable("id") Integer id,
                                                               @RequestBody SalaryIncrementsDTO salaryIncrementsDTO){
        return CustomResponse.build(salaryIncrementsService.updateByManager(id, salaryIncrementsDTO));
    }

    @PutMapping("/by-leader/{id}")
    public CustomResponse<SalaryIncrementsDTO> updateByLeader(@PathVariable("id") Integer id,
                                                              @RequestBody SalaryIncrementsDTO salaryIncrementsDTO){
        return CustomResponse.build(salaryIncrementsService.updateByLeader(id, salaryIncrementsDTO));
    }

    @PutMapping("/submit/{id}")
    public CustomResponse<SalaryIncrementsDTO> submit(@PathVariable("id") Integer id,
                                                      @RequestBody SalaryIncrementsDTO salaryIncrementsDTO){
        return CustomResponse.build(salaryIncrementsService.submitToLeader(id, salaryIncrementsDTO));
    }

    @DeleteMapping("/{id}")
    public CustomResponse<String> deleteById(@PathVariable("id") Integer id){
        salaryIncrementsService.deleteById(id);
        return CustomResponse.build(ErrorMessages.SUCCESS);
    }
}
