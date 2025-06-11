package com.ancora.mechanicapp.application;
import com.ancora.mechanicapp.domain.model.Order;
import com.ancora.mechanicapp.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repo;
    public OrderService(OrderRepository repo){ this.repo = repo; }
    public List<Order> list(){ return repo.findAll(); }
    public Order get(Long id){ return repo.findById(id).orElse(null); }
    public Order save(Order o){ return repo.save(o); }
    public void delete(Long id){ repo.deleteById(id); }
}
