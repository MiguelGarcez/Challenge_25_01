package com.ancora.mechanicapp.presentation;

import com.ancora.mechanicapp.application.PartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    private final PartService partService;

    public CatalogController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping
    public String search(@RequestParam(name = "q", required = false) String query, Model model) {
        if (query != null && !query.isBlank()) {
            model.addAttribute("results", partService.search(query));
        }
        return "catalog/search";
    }
}
