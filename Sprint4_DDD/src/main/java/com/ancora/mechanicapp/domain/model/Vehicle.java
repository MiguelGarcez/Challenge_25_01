package com.ancora.mechanicapp.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Vehicle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @jakarta.validation.constraints.NotNull(message = "Selecione uma marca")
    private Brand brand;

    @NotBlank(message = "Informe o modelo")
    private String model;

    @jakarta.validation.constraints.NotNull(message = "Ano é obrigatório")
    @jakarta.validation.constraints.Min(value = 1900, message = "Ano inválido")
    private Integer year;

    public Vehicle() {}
    public Vehicle(Brand brand, String model, Integer year) {
        this.brand = brand; this.model = model; this.year = year;
    }
    public Long getId() { return id; }
    public Brand getBrand() { return brand; }
    public String getModel() { return model; }
    public Integer getYear() { return year; }
    public void setId(Long id) { this.id = id; }
    public void setBrand(Brand brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }
    public void setYear(Integer year) { this.year = year; }
}
