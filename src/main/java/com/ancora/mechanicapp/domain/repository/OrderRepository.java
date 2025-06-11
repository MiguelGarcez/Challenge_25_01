package com.ancora.mechanicapp.domain.repository;
import com.ancora.mechanicapp.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<Order, Long> {}
