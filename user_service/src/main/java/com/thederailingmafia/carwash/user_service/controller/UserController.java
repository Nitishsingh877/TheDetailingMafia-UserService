package com.thederailingmafia.carwash.user_service.controller;

import com.thederailingmafia.carwash.user_service.dto.UserDto;
import com.thederailingmafia.carwash.user_service.model.UserModel;
import com.thederailingmafia.carwash.user_service.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @PostMapping("/signUp")
    public UserModel signUp(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto,"Email");

    }
}
