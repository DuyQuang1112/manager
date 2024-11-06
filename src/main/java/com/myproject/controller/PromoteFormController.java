package com.myproject.controller;

import com.myproject.commons.OctResponse;
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
    public OctResponse<List<PromoteFormDTO>> getAll(){
        return OctResponse.build(promoteFormService.getAll());
    }

    @GetMapping("/{id}")
    public OctResponse<PromoteFormDTO> getById(@PathVariable("id") Integer id){
        return OctResponse.build(promoteFormService.getById(id));
    }

    @GetMapping("leader-id/{id}")
    public OctResponse<List<PromoteFormDTO>> getByLeader(@PathVariable("id") Integer LeaderId){
        return OctResponse.build(promoteFormService.getByLeaderId(LeaderId));
    }

    @PostMapping
    public OctResponse<PromoteFormDTO> create(@RequestBody PromoteFormDTO promoteFormDTO){
        return OctResponse.build(promoteFormService.create(promoteFormDTO));
    }

    @PutMapping("/manager/{id}")
    public OctResponse<PromoteFormDTO> updateByManager(@PathVariable("id") Integer id,
                                                       @RequestBody PromoteFormDTO promoteFormDTO){
        return OctResponse.build(promoteFormService.updateByManager(id, promoteFormDTO));
    }

    @PutMapping("/by-leader/{id}")
    public OctResponse<PromoteFormDTO> updateByLeader(@PathVariable("id") Integer id,
                                                      @RequestBody PromoteFormDTO promoteFormDTO){
        return OctResponse.build(promoteFormService.updateByLeader(id, promoteFormDTO));
    }

    @PutMapping("/submit/{id}")
    public OctResponse<PromoteFormDTO> submit(@PathVariable("id") Integer id,
                                              @RequestBody PromoteFormDTO promoteFormDTO){
        return OctResponse.build(promoteFormService.submitToLeader(id, promoteFormDTO));
    }

    @DeleteMapping("/{id}")
    public OctResponse<String> deleteById(@PathVariable("id") Integer id){
        promoteFormService.deleteById(id);
        return OctResponse.build(ErrorMessages.SUCCESS);
    }
}
