package com.example.ProyectoCoolCorders.Models.Entity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Products")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    @JsonIgnore
    private Long id;

    @Column(updatable = false, nullable = false, length = 100, unique = true)
    private String uuid;

    @Column(nullable = false, length = 255, unique = true)
    private String fantasyName; 

    @Column(nullable = false, length = 500, unique = true)
    private String category;

    @Column(nullable = false, length = 500, unique = true)
    private String description;

    @Column(nullable = false, length = 500)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean available;

    @Builder.Default
    @OneToMany(mappedBy = "productUUID", cascade = CascadeType.MERGE, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonBackReference
    private List<Ordermodel> orderList = new ArrayList<>();
}
