package com.thederailingmafia.carwash.user_service.controller;

import com.thederailingmafia.carwash.user_service.dto.LoginResponseDto;
import com.thederailingmafia.carwash.user_service.dto.UserDto;
import com.thederailingmafia.carwash.user_service.model.UserModel;
import com.thederailingmafia.carwash.user_service.service.UserService;
import com.thederailingmafia.carwash.user_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @PostMapping("/signUp")
    public LoginResponseDto signUp(@RequestBody UserDto userDto) {
        UserModel user = userService.saveUser(userDto,"Email");
        String token = jwtUtil.generateToken(user.getEmail(), user.getUserRole().name());
        return new LoginResponseDto(token,userDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody UserDto userDto) throws Exception {
       return userService.loginUser(userDto.getEmail(),userDto.getPassword());
    }
}
