package com.thederailingmafia.carwash.user_service.dto;

import com.thederailingmafia.carwash.user_service.model.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    // Uncomment the address and phone number fields if needed
    // @NotBlank(message = "Address cannot be empty")
    // private String address;

    // @NotBlank(message = "Phone number cannot be empty")
    // @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    // private String phoneNumber;
}