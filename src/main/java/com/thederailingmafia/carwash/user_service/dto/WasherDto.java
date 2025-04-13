package com.thederailingmafia.carwash.user_service.dto;

import com.thederailingmafia.carwash.user_service.model.Washer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WasherDto {
    private String washerName;
    private String washerEmail;
    private Boolean isActive;

    public WasherDto(UserDto userDto) {
        this.washerName = userDto.getName();
        this.washerEmail = userDto.getEmail();
        this.isActive = true;
    }
}
