package com.myproject.controller;

import com.myproject.commons.CustomResponse;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.dto.PromoteFormDTO;
import com.myproject.service.PromoteFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/promote-form")
@RequiredArgsConstructor
public class PromoteFormController {
    private final PromoteFormService promoteFormService;

    @GetMapping
    public CustomResponse<List<PromoteFormDTO>> getAll(){
        return CustomResponse.build(promoteFormService.getAll());
    }

    @GetMapping("/{id}")
    public CustomResponse<PromoteFormDTO> getById(@PathVariable("id") Integer id){
        return CustomResponse.build(promoteFormService.getById(id));
    }

    @GetMapping("leader-id/{id}")
    public CustomResponse<List<PromoteFormDTO>> getByLeader(@PathVariable("id") Integer LeaderId){
        return CustomResponse.build(promoteFormService.getByLeaderId(LeaderId));
    }

    @PostMapping
    public CustomResponse<PromoteFormDTO> create(@RequestBody PromoteFormDTO promoteFormDTO){
        return CustomResponse.build(promoteFormService.create(promoteFormDTO));
    }

    @PutMapping("/manager/{id}")
    public CustomResponse<PromoteFormDTO> updateByManager(@PathVariable("id") Integer id,
                                                          @RequestBody PromoteFormDTO promoteFormDTO){
        return CustomResponse.build(promoteFormService.updateByManager(id, promoteFormDTO));
    }

    @PutMapping("/by-leader/{id}")
    public CustomResponse<PromoteFormDTO> updateByLeader(@PathVariable("id") Integer id,
                                                         @RequestBody PromoteFormDTO promoteFormDTO){
        return CustomResponse.build(promoteFormService.updateByLeader(id, promoteFormDTO));
    }

    @PutMapping("/submit/{id}")
    public CustomResponse<PromoteFormDTO> submit(@PathVariable("id") Integer id,
                                                 @RequestBody PromoteFormDTO promoteFormDTO){
        return CustomResponse.build(promoteFormService.submitToLeader(id, promoteFormDTO));
    }

    @DeleteMapping("/{id}")
    public CustomResponse<String> deleteById(@PathVariable("id") Integer id){
        promoteFormService.deleteById(id);
        return CustomResponse.build(ErrorMessages.SUCCESS);
    }
}
