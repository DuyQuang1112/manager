package com.myproject.controller;

import com.myproject.commons.OctResponse;
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
    public OctResponse<List<ProposeFormDTO>> getAll(){
        return OctResponse.build(proposeFormService.getAll());
    }

    @GetMapping("/{id}")
    public OctResponse<ProposeFormDTO> getById(@PathVariable("id") Integer id){
        return OctResponse.build(proposeFormService.getById(id));
    }

    @GetMapping("leader-id/{id}")
    public OctResponse<List<ProposeFormDTO>> getByLeader(@PathVariable("id") Integer LeaderId){
        return OctResponse.build(proposeFormService.getByLeaderId(LeaderId));
    }

    @PostMapping
    public OctResponse<ProposeFormDTO> create(@RequestBody ProposeFormDTO proposeFormDTO){
        return OctResponse.build(proposeFormService.create(proposeFormDTO));
    }

    @PutMapping("/manager/{id}")
    public OctResponse<ProposeFormDTO> updateByManager(@PathVariable("id") Integer id,
                                                       @RequestBody ProposeFormDTO proposeFormDTO){
        return OctResponse.build(proposeFormService.updateByManager(id, proposeFormDTO));
    }

    @PutMapping("/by-leader/{id}")
    public OctResponse<ProposeFormDTO> updateByLeader(@PathVariable("id") Integer id,
                                                      @RequestBody ProposeFormDTO proposeFormDTO){
        return OctResponse.build(proposeFormService.updateByLeader(id, proposeFormDTO));
    }

    @PutMapping("/submit/{id}")
    public OctResponse<ProposeFormDTO> submit(@PathVariable("id") Integer id,
                                              @RequestBody ProposeFormDTO proposeFormDTO){
        return OctResponse.build(proposeFormService.submitToLeader(id, proposeFormDTO));
    }

    @DeleteMapping("/{id}")
    public OctResponse<String> deleteById(@PathVariable("id") Integer id){
        proposeFormService.deleteById(id);
        return OctResponse.build(ErrorMessages.SUCCESS);
    }
}
