package com.ancora.mechanicapp.presentation;
import com.ancora.mechanicapp.application.BrandService;
import com.ancora.mechanicapp.domain.model.Brand;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private final BrandService service;
    public BrandController(BrandService s){ this.service = s; }

    @GetMapping
    public String list(Model model){
        model.addAttribute("brands", service.list());
        return "brands/list";
    }
    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("brand", new Brand());
        return "brands/form";
    }
    @PostMapping
    public String save(@Valid @ModelAttribute("brand") Brand brand,
                       BindingResult result) {

        if (result.hasErrors()) {
            return "brands/form";
        }

        service.save(brand);
        return "redirect:/brands";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("brand", service.get(id));
        return "brands/form";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/brands";
    }
}
