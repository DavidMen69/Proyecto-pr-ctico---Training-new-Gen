package com.example.ProyectoCoolCorders.Models.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Ordermodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    @JsonIgnore
    private Long id;

    @Column(nullable = false, length = 36, unique = true)
    private String uuid;

    @CreationTimestamp
    private LocalDateTime creationDateTime;

    @ManyToOne
    @JoinColumn(name = "client_document", nullable = false)
    private ClientModel clientDocument;

    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    private ProductModel productUUID;

    @Column(updatable = false,nullable = false, length = 20, unique = true)
    private int quantity;

    @Column(nullable = false, length = 500, unique = true)
    private String extraInformation;

    private double subTotal;

    private double tax;

    private double grandTotal;

    @Builder.Default
    private boolean delivered = false;

    private LocalDateTime deliveredDate;

}
