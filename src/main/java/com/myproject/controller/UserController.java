package com.myproject.controller;

import com.myproject.commons.CustomResponse;
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
    public CustomResponse<List<UserDTO>> logIn(){
        return CustomResponse.build(userService.getAllLeader());
    }
}
