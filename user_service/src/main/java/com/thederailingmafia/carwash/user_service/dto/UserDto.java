package com.thederailingmafia.carwash.user_service.dto;

import com.thederailingmafia.carwash.user_service.model.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
//    private String address;
//    private String phoneNumber;
}
