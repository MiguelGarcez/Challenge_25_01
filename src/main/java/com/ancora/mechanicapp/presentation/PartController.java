package com.ancora.mechanicapp.presentation;
import com.ancora.mechanicapp.application.BrandService;
import com.ancora.mechanicapp.application.PartService;
import com.ancora.mechanicapp.domain.model.Part;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/parts")
public class PartController {
    private final PartService service;
    private final BrandService brandService;
    public PartController(PartService s, BrandService bs){ this.service = s; this.brandService = bs; }

    @GetMapping
    public String list(Model model){
        model.addAttribute("parts", service.list());
        return "parts/list";
    }
    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("part", new Part());
        model.addAttribute("brands", brandService.list());
        return "parts/form";
    }

    @Autowired
    private Validator validator;

    @PostMapping
    public String save(@RequestParam Long brandId,
                       @ModelAttribute("part") Part part,
                       BindingResult result,
                       Model model) {

        part.setBrand(brandService.get(brandId));

        // executa validação manual
        validator.validate(part).forEach(v ->
                result.rejectValue(
                        v.getPropertyPath().toString(),   // campo
                        null,                             // código
                        v.getMessage())                   // mensagem
        );

        if (result.hasErrors()) {
            model.addAttribute("brands", brandService.list());
            return "parts/form";
        }
        service.save(part);
        return "redirect:/parts";
    }


    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("part", service.get(id));
        model.addAttribute("brands", brandService.list());
        return "parts/form";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/parts";
    }
}
