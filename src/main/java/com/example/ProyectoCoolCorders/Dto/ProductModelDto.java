package com.example.ProyectoCoolCorders.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
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




