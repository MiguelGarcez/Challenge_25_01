package com.ancora.mechanicapp.application;

import com.ancora.mechanicapp.domain.model.Part;
import com.ancora.mechanicapp.domain.repository.PartRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PartService {
    private final PartRepository repo;
    public PartService(PartRepository repo){ this.repo = repo; }
    public List<Part> list(){ return repo.findAll(); }
    public Part get(Long id){ return repo.findById(id).orElse(null); }
    public Part save(Part p){ return repo.save(p); }
    public void delete(Long id){ repo.deleteById(id); }
}
