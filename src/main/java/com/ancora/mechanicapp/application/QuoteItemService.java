package com.ancora.mechanicapp.application;

import com.ancora.mechanicapp.domain.model.QuoteItem;
import com.ancora.mechanicapp.domain.model.ServiceOrder;
import com.ancora.mechanicapp.domain.repository.QuoteItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteItemService {
    private final QuoteItemRepository repo;
    public QuoteItemService(QuoteItemRepository repo) { this.repo = repo; }

    public List<QuoteItem> list() { return repo.findAll(); }
    public List<QuoteItem> listByServiceOrder(ServiceOrder so) {
        return repo.findByServiceOrder(so);
    }
    public QuoteItem get(Long id) { return repo.findById(id).orElse(null); }
    public QuoteItem save(QuoteItem qi) { return repo.save(qi); }
    public void delete(Long id) { repo.deleteById(id); }
}
