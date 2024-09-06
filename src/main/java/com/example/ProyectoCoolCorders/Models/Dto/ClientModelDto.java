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
    private Long id;
    private String document;
    private String name;
    private String email;
    private String phone;
    private String deliveryAddress;
}
