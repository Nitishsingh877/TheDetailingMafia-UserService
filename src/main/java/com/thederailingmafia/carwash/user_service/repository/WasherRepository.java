package com.thederailingmafia.carwash.user_service.repository;

import com.thederailingmafia.carwash.user_service.model.UserModel;
import com.thederailingmafia.carwash.user_service.model.UserRole;
import com.thederailingmafia.carwash.user_service.model.Washer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WasherRepository extends JpaRepository<Washer,Long> {

}
