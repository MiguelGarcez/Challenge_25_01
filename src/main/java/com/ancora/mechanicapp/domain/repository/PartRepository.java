package com.ancora.mechanicapp.domain.repository;

import com.ancora.mechanicapp.domain.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PartRepository extends JpaRepository<Part, Long> {
    List<Part> findByModelContainingIgnoreCase(String model);
}
