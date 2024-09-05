package com.example.ProyectoCoolCorders.Models.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order")
public class Ordermodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    @JsonIgnore
    private Long id;

    @Column(updatable = false, nullable = false, length = 100, unique = true)
    private String productUuid;

    @Column(updatable = false,nullable = false, length = 20, unique = true)
    private int quantity;

    @Column(nullable = false, length = 500, unique = true)
    private String extraInformation;

    @Column(nullable = false, length = 36, unique = true)
    private String uuid;

    @Column(nullable = false)
    private LocalDateTime creationDateTime;

    @Column(nullable = false, length = 50)
    private String clientDocument;

    @Column(nullable = false)
    private double subTotal;

    @Column(nullable = false)
    private double tax;

    @Column(nullable = false)
    private double grandTotal;

    @Column(nullable = false)
    private boolean delivered;

    @Column
    private LocalDateTime deliveredDate;


}
