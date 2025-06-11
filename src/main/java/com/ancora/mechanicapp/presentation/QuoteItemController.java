package com.ancora.mechanicapp.presentation;

import com.ancora.mechanicapp.application.PartService;
import com.ancora.mechanicapp.application.QuoteItemService;
import com.ancora.mechanicapp.application.ServiceOrderService;
import com.ancora.mechanicapp.application.OrderService;
import com.ancora.mechanicapp.domain.model.Order;
import com.ancora.mechanicapp.domain.model.QuoteItem;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/quote-items")
public class QuoteItemController {
    private final QuoteItemService service;
    private final PartService partService;
    private final ServiceOrderService serviceOrderService;
    private final OrderService orderService;
    @Autowired
    private Validator validator;

    public QuoteItemController(QuoteItemService service,
                               PartService partService,
                               ServiceOrderService serviceOrderService,
                               OrderService orderService) {
        this.service = service;
        this.partService = partService;
        this.serviceOrderService = serviceOrderService;
        this.orderService = orderService;
    }

    @GetMapping
    public String list(@RequestParam(name = "so", required = false) Long soId,
                       Model model) {
        if (soId != null) {
            var so = serviceOrderService.get(soId);
            model.addAttribute("quoteItems", service.listByServiceOrder(so));
            model.addAttribute("selectedSo", soId);
        } else {
            model.addAttribute("quoteItems", service.list());
        }
        model.addAttribute("serviceOrders", serviceOrderService.list());
        return "quoteitems/list";
    }

    @GetMapping("/new")
    public String createForm(@RequestParam(name = "serviceOrderId", required = false) Long soId,
                             Model model) {
        var qi = new QuoteItem();
        if (soId != null) {
            qi.setServiceOrder(serviceOrderService.get(soId));
        }
        model.addAttribute("quoteItem", qi);
        model.addAttribute("parts", partService.list());
        model.addAttribute("serviceOrders", serviceOrderService.list());
        model.addAttribute("selectedSo", soId);
        return "quoteitems/form";
    }

    @PostMapping
    public String save(@RequestParam Long serviceOrderId,
                       @RequestParam Long partId,
                       @ModelAttribute("quoteItem") QuoteItem qi,
                       BindingResult result,
                       Model model) {
        qi.setServiceOrder(serviceOrderService.get(serviceOrderId));
        qi.setPart(partService.get(partId));

        validator.validate(qi).forEach(v ->
                result.rejectValue(v.getPropertyPath().toString(), null, v.getMessage())
        );

        if (result.hasErrors()) {
            model.addAttribute("parts", partService.list());
            model.addAttribute("serviceOrders", serviceOrderService.list());
            model.addAttribute("selectedSo", serviceOrderId);
            return "quoteitems/form";
        }

        service.save(qi);
        return "redirect:/quote-items?so=" + serviceOrderId;
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        var qi = service.get(id);
        model.addAttribute("quoteItem", qi);
        model.addAttribute("parts", partService.list());
        model.addAttribute("serviceOrders", serviceOrderService.list());
        model.addAttribute("selectedSo", qi.getServiceOrder().getId());
        return "quoteitems/form";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        var qi = service.get(id);
        var soId = qi.getServiceOrder().getId();
        service.delete(id);
        return "redirect:/quote-items?so=" + soId;
    }

    @PostMapping("/convert")
    public String convert(@RequestParam Long serviceOrderId) {
        var so = serviceOrderService.get(serviceOrderId);
        for (var item : service.listByServiceOrder(so)) {
            orderService.save(new Order(item.getPart(), item.getQuantity()));
            service.delete(item.getId());
        }
        return "redirect:/orders";
    }
}
