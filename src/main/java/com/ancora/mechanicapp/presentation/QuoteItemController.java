package com.ancora.mechanicapp.presentation;

import com.ancora.mechanicapp.application.PartService;
import com.ancora.mechanicapp.application.QuoteItemService;
import com.ancora.mechanicapp.application.ServiceOrderService;
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
    @Autowired
    private Validator validator;

    public QuoteItemController(QuoteItemService service,
                               PartService partService,
                               ServiceOrderService serviceOrderService) {
        this.service = service;
        this.partService = partService;
        this.serviceOrderService = serviceOrderService;
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
    public String createForm(Model model) {
        model.addAttribute("quoteItem", new QuoteItem());
        model.addAttribute("parts", partService.list());
        model.addAttribute("serviceOrders", serviceOrderService.list());
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
            return "quoteitems/form";
        }

        service.save(qi);
        return "redirect:/quote-items?so=" + serviceOrderId;
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("quoteItem", service.get(id));
        model.addAttribute("parts", partService.list());
        model.addAttribute("serviceOrders", serviceOrderService.list());
        return "quoteitems/form";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        var qi = service.get(id);
        var soId = qi.getServiceOrder().getId();
        service.delete(id);
        return "redirect:/quote-items?so=" + soId;
    }
}
