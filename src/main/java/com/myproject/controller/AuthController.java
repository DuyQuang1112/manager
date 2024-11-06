package com.myproject.controller;

import com.myproject.commons.OctResponse;
import com.myproject.dto.JwtResponse;
import com.myproject.dto.user.UserDTO;
import com.myproject.dto.user.UserLogin;
import com.myproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public OctResponse<UserDTO> register(@RequestBody UserDTO userDTO){
        return OctResponse.build(userService.createUser(userDTO));
    }

    @PostMapping("/log-in")
    public OctResponse<JwtResponse> logIn(@RequestBody UserLogin userLogin){
        return OctResponse.build(userService.logIn(userLogin));
    }

}
