package com.ancora.mechanicapp.presentation;
import com.ancora.mechanicapp.application.BrandService;
import com.ancora.mechanicapp.application.VehicleService;
import com.ancora.mechanicapp.domain.model.Vehicle;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService service;
    private final BrandService brandService;
    public VehicleController(VehicleService s, BrandService bs){ this.service = s; this.brandService = bs; }

    @GetMapping
    public String list(Model model){
        model.addAttribute("vehicles", service.list());
        return "vehicles/list";
    }
    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("brands", brandService.list());
        return "vehicles/form";
    }

    @Autowired
    private Validator validator;

    @PostMapping
    public String save(@RequestParam Long brandId,
                       @ModelAttribute("vehicle") Vehicle vehicle,
                       BindingResult result,
                       Model model) {

        vehicle.setBrand(brandService.get(brandId));

        // executa validação manual
        validator.validate(vehicle).forEach(v ->
                result.rejectValue(
                        v.getPropertyPath().toString(),   // campo
                        null,                             // código
                        v.getMessage())                   // mensagem
        );

        if (result.hasErrors()) {
            model.addAttribute("brands", brandService.list());
            return "vehicles/form";
        }
        service.save(vehicle);
        return "redirect:/vehicles";
    }


    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("vehicle", service.get(id));
        model.addAttribute("brands", brandService.list());
        return "vehicles/form";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/vehicles";
    }
}
