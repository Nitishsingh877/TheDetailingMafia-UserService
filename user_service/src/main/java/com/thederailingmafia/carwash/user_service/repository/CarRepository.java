package com.thederailingmafia.carwash.user_service.repository;

import com.thederailingmafia.carwash.user_service.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
