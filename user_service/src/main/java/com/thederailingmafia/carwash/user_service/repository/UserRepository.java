package com.thederailingmafia.carwash.user_service.repository;


import com.thederailingmafia.carwash.user_service.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

}
