package com.ancora.mechanicapp.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @jakarta.validation.constraints.NotNull(message = "Selecione um veículo")
    private Vehicle vehicle;

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade mínima é 1")
    private Integer quantity;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Order() {}
    public Order(Vehicle vehicle, Integer quantity) {
        this.vehicle = vehicle; this.quantity = quantity;
    }
    public Long getId() { return id; }
    public Vehicle getVehicle() { return vehicle; }
    public Integer getQuantity() { return quantity; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setId(Long id) { this.id = id; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setCreatedAt(LocalDateTime t){this.createdAt = t;}
}
