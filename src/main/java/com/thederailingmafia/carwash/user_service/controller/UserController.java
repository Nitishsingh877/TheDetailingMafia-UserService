package com.thederailingmafia.carwash.user_service.controller;

import com.thederailingmafia.carwash.user_service.dto.LoginResponseDto;
import com.thederailingmafia.carwash.user_service.dto.UserDto;
import com.thederailingmafia.carwash.user_service.dto.UserProfileResponse;
import com.thederailingmafia.carwash.user_service.model.UserModel;
import com.thederailingmafia.carwash.user_service.repository.UserRepository;
import com.thederailingmafia.carwash.user_service.service.UserService;
import com.thederailingmafia.carwash.user_service.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
@Tag(name = "User Management", description = "User authentication and profile management APIs") // Swagger Tag
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/health")
    @Operation(summary = "Check API Health", description = "Returns 'OK' if API is running")
    public String health() {
        log.info(userService.toString());
        return "OK";
    }

    @PostMapping("/signUp")
    @Operation(summary = "User Sign Up", description = "Registers a new user and returns JWT token")
    public LoginResponseDto signUp(@RequestBody UserDto userDto) {
        UserModel user = userService.saveUser(userDto,"Email");
        String token = jwtUtil.generateToken(user.getEmail(), user.getUserRole().name());
        log.info("signup for the user " + user.getEmail());
        return new LoginResponseDto(token,userDto);
    }

    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Authenticates user and returns JWT token")
    public LoginResponseDto login(@RequestBody UserDto userDto) throws Exception {
        log.info("login for the user " + userDto.getEmail());
       return userService.loginUser(userDto.getEmail(),userDto.getPassword());
    }

    @GetMapping("/profile")
    @Operation(summary = "Get User Profile", description = "Fetches the profile of the authenticated user")
    public UserProfileResponse getProfile(Authentication authentication) {
        // Get authenticated user's email from SecurityContext
//        System.out.println("HERE I AM");
        String email = authentication.getName(); // Email from JWT (set by JwtRequestFilter)
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        log.info("get profile for the user " + user.getEmail());
        log.debug("get profile for the user " + user.getEmail());
        return new UserProfileResponse(user.getName(), user.getEmail(), user.getUserRole());

    }


    @PutMapping("/update/profile")
    @Operation(summary = "Update Profile", description = "Updates the profile of the authenticated user")
    public UserProfileResponse updateProfile(@RequestBody UserProfileResponse userProfileResponse, Authentication authentication)  {
        String email = authentication.getName();

        UserProfileResponse updatedProfile = userService.updateUserProfile(email,userProfileResponse);
        return updatedProfile;

    }
}
