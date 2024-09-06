package com.example.ProyectoCoolCorders.Models.Dto;

import java.math.BigDecimal;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModelDto {

    // Definición de atributos del DTO
    public String uuid;
    public String fantasyName;
    public String description;
    public BigDecimal price;
    public boolean available;
    public String category;
    


}




