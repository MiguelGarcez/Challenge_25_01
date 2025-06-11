package com.ancora.mechanicapp.domain.model;

import jakarta.persistence.*;

@Entity
public class Brand {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @jakarta.validation.constraints.NotBlank(message = "O nome da marca é obrigatório")
    private String name;
    public Brand() {}
    public Brand(String name) { this.name = name; }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}
