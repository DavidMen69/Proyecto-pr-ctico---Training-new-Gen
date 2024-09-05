package com.example.ProyectoCoolCorders.Models.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderModelDto {
        private String uuid;
        private LocalDateTime creationDateTime;
        private String clientDocument;
        private String productUuid;
        private int quantity;
        private String extraInformation;
        private double subTotal;
        private double tax;
        private double grandTotal;
        private boolean delivered;
        private LocalDateTime deliveredDate;
}
