package com.myproject.controller;

import com.myproject.commons.CustomResponse;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.dto.ProposeFormDTO;
import com.myproject.service.ProposeFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/propose-form")
@RequiredArgsConstructor
public class ProposeFormController {

    private final ProposeFormService proposeFormService;

    @GetMapping
    public CustomResponse<List<ProposeFormDTO>> getAll(){
        return CustomResponse.build(proposeFormService.getAll());
    }

    @GetMapping("/{id}")
    public CustomResponse<ProposeFormDTO> getById(@PathVariable("id") Integer id){
        return CustomResponse.build(proposeFormService.getById(id));
    }

    @GetMapping("leader-id/{id}")
    public CustomResponse<List<ProposeFormDTO>> getByLeader(@PathVariable("id") Integer LeaderId){
        return CustomResponse.build(proposeFormService.getByLeaderId(LeaderId));
    }

    @PostMapping
    public CustomResponse<ProposeFormDTO> create(@RequestBody ProposeFormDTO proposeFormDTO){
        return CustomResponse.build(proposeFormService.create(proposeFormDTO));
    }

    @PutMapping("/manager/{id}")
    public CustomResponse<ProposeFormDTO> updateByManager(@PathVariable("id") Integer id,
                                                          @RequestBody ProposeFormDTO proposeFormDTO){
        return CustomResponse.build(proposeFormService.updateByManager(id, proposeFormDTO));
    }

    @PutMapping("/by-leader/{id}")
    public CustomResponse<ProposeFormDTO> updateByLeader(@PathVariable("id") Integer id,
                                                         @RequestBody ProposeFormDTO proposeFormDTO){
        return CustomResponse.build(proposeFormService.updateByLeader(id, proposeFormDTO));
    }

    @PutMapping("/submit/{id}")
    public CustomResponse<ProposeFormDTO> submit(@PathVariable("id") Integer id,
                                                 @RequestBody ProposeFormDTO proposeFormDTO){
        return CustomResponse.build(proposeFormService.submitToLeader(id, proposeFormDTO));
    }

    @DeleteMapping("/{id}")
    public CustomResponse<String> deleteById(@PathVariable("id") Integer id){
        proposeFormService.deleteById(id);
        return CustomResponse.build(ErrorMessages.SUCCESS);
    }
}
