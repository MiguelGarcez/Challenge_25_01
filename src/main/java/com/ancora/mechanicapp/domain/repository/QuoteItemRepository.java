package com.ancora.mechanicapp.domain.repository;

import com.ancora.mechanicapp.domain.model.QuoteItem;
import com.ancora.mechanicapp.domain.model.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteItemRepository extends JpaRepository<QuoteItem, Long> {
    List<QuoteItem> findByServiceOrder(ServiceOrder serviceOrder);
}
