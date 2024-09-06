package com.example.ProyectoCoolCorders.Models.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientModelDto {
    public String document;
    public String name; 
    public String email;
    public String phone;
    public String deliveryAddress;
}
