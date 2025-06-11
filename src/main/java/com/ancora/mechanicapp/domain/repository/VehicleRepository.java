package com.ancora.mechanicapp.domain.repository;
import com.ancora.mechanicapp.domain.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {}
