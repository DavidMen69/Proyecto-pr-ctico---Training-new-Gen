package com.example.ProyectoCoolCorders.Models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GenerationType;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Products")
public class ProductModels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(updatable = false, nullable = false, length = 20, unique = true)
    private String uuid;

    @Column(nullable = false, length = 255, unique = true)
    private String fantasyName; 

    @Column(nullable = false, length = 500, unique = true)
    private String category;

    @Column(nullable = false, length = 500, unique = true)
    private String description;

    @Column(nullable = false, length = 500)
    private float price;

    @Column(nullable = false)
    private boolean available;
}
