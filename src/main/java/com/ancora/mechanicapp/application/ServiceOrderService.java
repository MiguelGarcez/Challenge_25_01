package com.ancora.mechanicapp.application;

import com.ancora.mechanicapp.domain.model.ServiceOrder;
import com.ancora.mechanicapp.domain.repository.ServiceOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceOrderService {
    private final ServiceOrderRepository repo;

    public ServiceOrderService(ServiceOrderRepository repo) {
        this.repo = repo;
    }

    public List<ServiceOrder> list() { return repo.findAll(); }
    public ServiceOrder get(Long id) { return repo.findById(id).orElse(null); }
    public ServiceOrder save(ServiceOrder so) { return repo.save(so); }
    public void delete(Long id) { repo.deleteById(id); }
}
