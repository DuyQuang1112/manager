package com.myproject.service;

import com.myproject.dto.PromoteFormDTO;

import java.util.List;

public interface PromoteFormService {

    List<PromoteFormDTO> getAll();

    PromoteFormDTO getById(Integer id);

    List<PromoteFormDTO> getByLeaderId(Integer leaderId);

    PromoteFormDTO create(PromoteFormDTO promoteFormDTO);

    PromoteFormDTO updateByManager(Integer id , PromoteFormDTO promoteFormDTO);

    PromoteFormDTO updateByLeader(Integer id, PromoteFormDTO promoteFormDTO);

    PromoteFormDTO submitToLeader(Integer id, PromoteFormDTO promoteFormDTO);

    void deleteById(Integer id);
}
