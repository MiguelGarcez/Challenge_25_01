package com.ancora.mechanicapp.presentation;
import com.ancora.mechanicapp.application.OrderService;
import com.ancora.mechanicapp.application.VehicleService;
import com.ancora.mechanicapp.domain.model.Order;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;
    private final VehicleService vehicleService;

    @Autowired
    private Validator validator;

    public OrderController(OrderService orderService,
                           VehicleService vehicleService) {
        this.service  = orderService;
        this.vehicleService = vehicleService;
    }
    @GetMapping
    public String list(Model model){
        model.addAttribute("orders", service.list());
        return "orders/list";
    }
    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("order", new Order());
        model.addAttribute("vehicles", vehicleService.list());
        return "orders/form";
    }

    @PostMapping
    public String save(@RequestParam Long vehicleId,
                       @ModelAttribute("order") Order order,
                       BindingResult result,
                       Model model) {

        order.setVehicle(vehicleService.get(vehicleId));

        validator.validate(order).forEach(v ->
                result.rejectValue(
                        v.getPropertyPath().toString(),
                        null,
                        v.getMessage())
        );

        if (result.hasErrors()) {
            model.addAttribute("vehicles", vehicleService.list());
            return "orders/form";
        }

        service.save(order);
        return "redirect:/orders";
    }


    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("order", service.get(id));
        model.addAttribute("vehicles", vehicleService.list());
        return "orders/form";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/orders";
    }
}
