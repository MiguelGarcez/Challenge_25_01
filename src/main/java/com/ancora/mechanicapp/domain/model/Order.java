package com.ancora.mechanicapp.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import com.ancora.mechanicapp.domain.model.Part;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @jakarta.validation.constraints.NotNull(message = "Selecione uma peça")
    private Part part;

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade mínima é 1")
    private Integer quantity;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Order() {}
    public Order(Part part, Integer quantity) {
        this.part = part; this.quantity = quantity;
    }
    public Long getId() { return id; }
    public Part getPart() { return part; }
    public Integer getQuantity() { return quantity; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setId(Long id) { this.id = id; }
    public void setPart(Part part) { this.part = part; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setCreatedAt(LocalDateTime t){this.createdAt = t;}
}
