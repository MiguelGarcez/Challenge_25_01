package com.ancora.mechanicapp.application;
import com.ancora.mechanicapp.domain.model.Brand;
import com.ancora.mechanicapp.domain.repository.BrandRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BrandService {
    private final BrandRepository repo;
    public BrandService(BrandRepository repo) { this.repo = repo; }
    public List<Brand> list(){ return repo.findAll(); }
    public Brand get(Long id){ return repo.findById(id).orElse(null); }
    public Brand save(Brand b){ return repo.save(b); }
    public void delete(Long id){ repo.deleteById(id); }
}
