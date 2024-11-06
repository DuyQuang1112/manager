package com.myproject.controller;

import com.myproject.commons.OctResponse;
import com.myproject.dto.user.UserDTO;
import com.myproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get-leader")
    public OctResponse<List<UserDTO>> logIn(){
        return OctResponse.build(userService.getAllLeader());
    }
}
