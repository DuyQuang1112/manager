package com.myproject.controller;

import com.myproject.commons.OctResponse;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.dto.ProfileEndDTO;
import com.myproject.service.ProfileEndService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profile-end")
@RequiredArgsConstructor
public class ProfileEndController {
    private final ProfileEndService profileEndService;

    @GetMapping
    public OctResponse<List<ProfileEndDTO>> getAll(){
        return OctResponse.build(profileEndService.getAll());
    }

    @GetMapping("/{id}")
    public OctResponse<ProfileEndDTO> getById(@PathVariable("id") Integer id){
        return OctResponse.build(profileEndService.getById(id));
    }

    @GetMapping("leader-id/{id}")
    public OctResponse<List<ProfileEndDTO>> getByLeader(@PathVariable("id") Integer LeaderId){
        return OctResponse.build(profileEndService.getByLeaderId(LeaderId));
    }

    @PostMapping
    public OctResponse<ProfileEndDTO> create(@RequestBody ProfileEndDTO profileEndDTO){
        return OctResponse.build(profileEndService.create(profileEndDTO));
    }

    @PutMapping("/manager/{id}")
    public OctResponse<ProfileEndDTO> updateByManager(@PathVariable("id") Integer id,
                                                      @RequestBody ProfileEndDTO profileEndDTO){
        return OctResponse.build(profileEndService.updateByManager(id, profileEndDTO));
    }

    @PutMapping("/by-leader/{id}")
    public OctResponse<ProfileEndDTO> updateByLeader(@PathVariable("id") Integer id,
                                                     @RequestBody ProfileEndDTO profileEndDTO){
        return OctResponse.build(profileEndService.updateByLeader(id, profileEndDTO));
    }

    @PutMapping("/submit/{id}")
    public OctResponse<ProfileEndDTO> submit(@PathVariable("id") Integer id,
                                             @RequestBody ProfileEndDTO profileEndDTO){
        return OctResponse.build(profileEndService.submitToLeader(id, profileEndDTO));
    }

    @DeleteMapping("/{id}")
    public OctResponse<String> deleteById(@PathVariable("id") Integer id){
        profileEndService.deleteById(id);
        return OctResponse.build(ErrorMessages.SUCCESS);
    }

}
