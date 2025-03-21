package com.thederailingmafia.carwash.user_service.service;

import com.thederailingmafia.carwash.user_service.dto.CustomerDto;
import com.thederailingmafia.carwash.user_service.dto.UserDto;
import com.thederailingmafia.carwash.user_service.dto.WasherDto;
import com.thederailingmafia.carwash.user_service.model.UserModel;
import com.thederailingmafia.carwash.user_service.model.UserRole;
import com.thederailingmafia.carwash.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserModel saveUser(UserDto userDto,String authToken) {
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
    }





}
