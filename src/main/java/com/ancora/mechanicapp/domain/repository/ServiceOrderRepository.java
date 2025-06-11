package com.ancora.mechanicapp.domain.repository;

import com.ancora.mechanicapp.domain.model.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {}
