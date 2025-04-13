package com.thederailingmafia.carwash.user_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "washer")
@NoArgsConstructor
public class Washer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Washer_id;
    private String WasherName;
    private String WasherEmail;
    private boolean IsActive;


    @OneToOne
    @JoinColumn(name = "User_id",nullable = false,unique = true)
    private UserModel user;

    private String phoneNumber;
    private String address;

    public Washer(String WasherName, String WasherEmail, String phoneNumber, String address,boolean IsActive) {
        this.WasherName = WasherName;
        this.WasherEmail = WasherEmail;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.IsActive = IsActive;
    }

}
