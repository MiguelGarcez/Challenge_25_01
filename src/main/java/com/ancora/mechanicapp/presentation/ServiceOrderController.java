package com.ancora.mechanicapp.presentation;

import com.ancora.mechanicapp.application.ServiceOrderService;
import com.ancora.mechanicapp.domain.model.ServiceOrder;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/service-orders")
public class ServiceOrderController {
    private final ServiceOrderService service;

    public ServiceOrderController(ServiceOrderService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("serviceOrders", service.list());
        return "serviceorders/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("serviceOrder", new ServiceOrder());
        return "serviceorders/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("serviceOrder") ServiceOrder so,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "serviceorders/form";
        }

        service.save(so);
        return "redirect:/service-orders";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("serviceOrder", service.get(id));
        return "serviceorders/form";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/service-orders";
    }
}
