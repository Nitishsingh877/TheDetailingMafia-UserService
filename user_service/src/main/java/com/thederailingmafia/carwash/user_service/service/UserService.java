package com.thederailingmafia.carwash.user_service.service;

import com.thederailingmafia.carwash.user_service.dto.CustomerDto;
import com.thederailingmafia.carwash.user_service.dto.LoginResponseDto;
import com.thederailingmafia.carwash.user_service.dto.UserDto;
import com.thederailingmafia.carwash.user_service.dto.WasherDto;
import com.thederailingmafia.carwash.user_service.model.UserModel;
import com.thederailingmafia.carwash.user_service.model.UserRole;
import com.thederailingmafia.carwash.user_service.repository.UserRepository;
import com.thederailingmafia.carwash.user_service.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    @Autowired
    private CustomerService customerService;
    @Autowired
    private WasherService washerService;

    @Autowired
    private JwtUtil jwtUtil;

    public UserModel saveUser(UserDto userDto, String authToken) {
        UserModel userModel = new UserModel(userDto.getName(), userDto.getPassword(), userDto.getEmail(), userDto.getUserRole(),null, null,authToken);
        if(authToken.equals("Email")) {
            userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        }
         userRepository.save(userModel);

        if(userModel.getUserRole() == UserRole.CUSTOMER) {
            CustomerDto customerDto = new CustomerDto(userDto);
            customerService.CreateCustomer(userModel,customerDto);
        }

        if(userModel.getUserRole() == UserRole.WASHER){
            WasherDto washerDto = new WasherDto(userDto);
            washerService.SaveWasher(userModel,washerDto);

        }
        return userModel;
       // return jwtUtil.generateToken(userModel.getEmail(), userModel.getUserRole().name());
    }

    @Transactional
    public LoginResponseDto loginUser(String email, String password) throws Exception, AuthenticationException, UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("User with email " + email + " not found"));

        if(!user.getAuth().equals("Email")) {
           throw new Exception("Wrong way of login");
        }

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Wrong password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getUserRole().name());
        UserDto userDto = new UserDto(user.getName(), user.getEmail(), null,null,user.getUserRole());

        return new LoginResponseDto(token,userDto);
    }
}
