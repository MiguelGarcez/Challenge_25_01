package com.ancora.mechanicapp.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_orders")
public class ServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    private String description;

    @NotBlank(message = "Placa obrigatória")
    private String carPlate;

    @NotBlank(message = "Modelo do carro é obrigatório")
    private String carModel;

    @NotBlank(message = "Nome do cliente é obrigatório")
    private String customerName;

    @NotBlank(message = "Contato do cliente é obrigatório")
    private String customerContact;

    private LocalDateTime createdAt = LocalDateTime.now();

    public ServiceOrder() {}

    public ServiceOrder(String description,
                        String carPlate,
                        String carModel,
                        String customerName,
                        String customerContact) {
        this.description = description;
        this.carPlate = carPlate;
        this.carModel = carModel;
        this.customerName = customerName;
        this.customerContact = customerContact;
    }

    public Long getId() { return id; }
    public String getDescription() { return description; }
    public String getCarPlate() { return carPlate; }
    public String getCarModel() { return carModel; }
    public String getCustomerName() { return customerName; }
    public String getCustomerContact() { return customerContact; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setDescription(String description) { this.description = description; }
    public void setCarPlate(String carPlate) { this.carPlate = carPlate; }
    public void setCarModel(String carModel) { this.carModel = carModel; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setCustomerContact(String customerContact) { this.customerContact = customerContact; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
