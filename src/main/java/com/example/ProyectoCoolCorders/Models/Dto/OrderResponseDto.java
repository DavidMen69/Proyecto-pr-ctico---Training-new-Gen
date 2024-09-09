package com.example.ProyectoCoolCorders.Models.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponseDto {
    private String uuid;
    private LocalDateTime creationDateTime;
    private String clientDocument;
    private String productUUID;
    private int quantity;
    private String extraInformation;
    private double subTotal;
    private double tax;
    private double grandTotal;
    private boolean delivered;
    private LocalDateTime deliveredDate;
}
