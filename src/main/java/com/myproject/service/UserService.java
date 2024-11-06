package com.myproject.service;

import com.myproject.dto.JwtResponse;
import com.myproject.dto.user.UserDTO;
import com.myproject.dto.user.UserLogin;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    JwtResponse logIn(UserLogin userLogin);

    List<UserDTO> getAllLeader();

    UserDTO getByUsername(String username);

}
