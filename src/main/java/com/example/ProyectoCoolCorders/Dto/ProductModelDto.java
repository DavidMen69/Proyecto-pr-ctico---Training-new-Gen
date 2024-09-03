package com.example.ProyectoCoolCorders.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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




