package com.example.ProyectoCoolCorders.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Clients")
public class ClientsModels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(updatable = false, nullable = false, length = 20, unique = true)
    private String document;

    @Column(nullable = false, length = 255)
    private String name; 
    
    @Column(nullable = false, length = 255, unique = true)
    private String email;

    @Column(nullable = false, length = 10, unique = true)
    private String phone;

    @Column(nullable = false, length = 500, unique = true)
    private String deliveryAddress;

}
