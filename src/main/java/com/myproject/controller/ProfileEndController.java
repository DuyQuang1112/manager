package com.myproject.controller;

import com.myproject.commons.CustomResponse;
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
    public CustomResponse<List<ProfileEndDTO>> getAll(){
        return CustomResponse.build(profileEndService.getAll());
    }

    @GetMapping("/{id}")
    public CustomResponse<ProfileEndDTO> getById(@PathVariable("id") Integer id){
        return CustomResponse.build(profileEndService.getById(id));
    }

    @GetMapping("leader-id/{id}")
    public CustomResponse<List<ProfileEndDTO>> getByLeader(@PathVariable("id") Integer LeaderId){
        return CustomResponse.build(profileEndService.getByLeaderId(LeaderId));
    }

    @PostMapping
    public CustomResponse<ProfileEndDTO> create(@RequestBody ProfileEndDTO profileEndDTO){
        return CustomResponse.build(profileEndService.create(profileEndDTO));
    }

    @PutMapping("/manager/{id}")
    public CustomResponse<ProfileEndDTO> updateByManager(@PathVariable("id") Integer id,
                                                         @RequestBody ProfileEndDTO profileEndDTO){
        return CustomResponse.build(profileEndService.updateByManager(id, profileEndDTO));
    }

    @PutMapping("/by-leader/{id}")
    public CustomResponse<ProfileEndDTO> updateByLeader(@PathVariable("id") Integer id,
                                                        @RequestBody ProfileEndDTO profileEndDTO){
        return CustomResponse.build(profileEndService.updateByLeader(id, profileEndDTO));
    }

    @PutMapping("/submit/{id}")
    public CustomResponse<ProfileEndDTO> submit(@PathVariable("id") Integer id,
                                                @RequestBody ProfileEndDTO profileEndDTO){
        return CustomResponse.build(profileEndService.submitToLeader(id, profileEndDTO));
    }

    @DeleteMapping("/{id}")
    public CustomResponse<String> deleteById(@PathVariable("id") Integer id){
        profileEndService.deleteById(id);
        return CustomResponse.build(ErrorMessages.SUCCESS);
    }

}
