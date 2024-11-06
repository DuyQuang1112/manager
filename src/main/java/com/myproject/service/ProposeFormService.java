package com.myproject.service;

import com.myproject.dto.ProposeFormDTO;

import java.util.List;

public interface ProposeFormService {

    List<ProposeFormDTO> getAll();

    ProposeFormDTO getById(Integer id);

    List<ProposeFormDTO> getByLeaderId(Integer leaderId);

    ProposeFormDTO create(ProposeFormDTO proposeFormDTO);

    ProposeFormDTO updateByManager(Integer id , ProposeFormDTO proposeFormDTO);

    ProposeFormDTO updateByLeader(Integer id, ProposeFormDTO proposeFormDTO);

    ProposeFormDTO submitToLeader(Integer id, ProposeFormDTO proposeFormDTO);

    void deleteById(Integer id);
}
