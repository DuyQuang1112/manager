package com.myproject.service;

import com.myproject.dto.RoleDTO;

public interface RoleService {
    RoleDTO getRoleById(Integer id);
    RoleDTO getRoleByName(String roleName);
}
