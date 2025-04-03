package com.thederailingmafia.carwash.user_service.controller;

import com.thederailingmafia.carwash.user_service.dto.LoginResponseDto;
import com.thederailingmafia.carwash.user_service.dto.UserDto;
import com.thederailingmafia.carwash.user_service.dto.UserProfileResponse;
import com.thederailingmafia.carwash.user_service.model.UserModel;
import com.thederailingmafia.carwash.user_service.repository.UserRepository;
import com.thederailingmafia.carwash.user_service.service.UserService;
import com.thederailingmafia.carwash.user_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

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

    @GetMapping("/profile")
    public UserProfileResponse getProfile(Authentication authentication) {
        // Get authenticated user's email from SecurityContext
//        System.out.println("HERE I AM");
        String email = authentication.getName(); // Email from JWT (set by JwtRequestFilter)
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserProfileResponse(user.getName(), user.getEmail(), user.getUserRole());

    }


    @PutMapping("/update/profile")
    public UserProfileResponse updateProfile(@RequestBody UserProfileResponse userProfileResponse, Authentication authentication)  {
        String email = authentication.getName();

        UserProfileResponse updatedProfile = userService.updateUserProfile(email,userProfileResponse);
        return updatedProfile;

    }



}
