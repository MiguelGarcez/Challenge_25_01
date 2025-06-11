package com.ancora.mechanicapp.application;
import com.ancora.mechanicapp.domain.model.Vehicle;
import com.ancora.mechanicapp.domain.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository repo;
    public VehicleService(VehicleRepository repo){ this.repo = repo; }
    public List<Vehicle> list(){ return repo.findAll(); }
    public Vehicle get(Long id){ return repo.findById(id).orElse(null); }
    public Vehicle save(Vehicle v){ return repo.save(v); }
    public void delete(Long id){ repo.deleteById(id); }
}
