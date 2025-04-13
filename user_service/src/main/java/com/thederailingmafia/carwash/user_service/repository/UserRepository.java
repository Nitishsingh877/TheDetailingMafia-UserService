package com.thederailingmafia.carwash.user_service.repository;


import com.thederailingmafia.carwash.user_service.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);

}
