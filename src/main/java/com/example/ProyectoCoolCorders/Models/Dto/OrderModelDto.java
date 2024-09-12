package com.example.ProyectoCoolCorders.Models.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class OrderModelDto {

        public String uuid;
        public LocalDateTime creationDateTime;
        public String clientDocument;
        public String productUUID;
        public int quantity;
        public String extraInformation;
        public double subTotal;
        public double tax;
        public double grandTotal;
        public boolean delivered;
        public LocalDateTime deliveredDate;

}
