package com.myproject.service;

import com.myproject.dto.ProfileEndDTO;

import java.util.List;

public interface ProfileEndService {
    List<ProfileEndDTO> getAll();

    ProfileEndDTO getById(Integer id);

    List<ProfileEndDTO> getByLeaderId(Integer leaderId);

    ProfileEndDTO create(ProfileEndDTO profileEndDTO);

    ProfileEndDTO updateByManager(Integer id , ProfileEndDTO profileEndDTO);

    ProfileEndDTO updateByLeader(Integer id, ProfileEndDTO profileEndDTO);

    ProfileEndDTO submitToLeader(Integer id, ProfileEndDTO profileEndDTO);

    void deleteById(Integer id);
}
