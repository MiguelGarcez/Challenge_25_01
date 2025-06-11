package com.ancora.mechanicapp.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "quote_items")
public class QuoteItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @NotNull(message = "Selecione a OS")
    private ServiceOrder serviceOrder;

    @ManyToOne(optional = false)
    @NotNull(message = "Selecione uma peça")
    private Part part;

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade mínima é 1")
    private Integer quantity;

    public QuoteItem() {}

    public QuoteItem(ServiceOrder serviceOrder, Part part, Integer quantity) {
        this.serviceOrder = serviceOrder;
        this.part = part;
        this.quantity = quantity;
    }

    public Long getId() { return id; }
    public ServiceOrder getServiceOrder() { return serviceOrder; }
    public Part getPart() { return part; }
    public Integer getQuantity() { return quantity; }

    public void setId(Long id) { this.id = id; }
    public void setServiceOrder(ServiceOrder serviceOrder) { this.serviceOrder = serviceOrder; }
    public void setPart(Part part) { this.part = part; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
