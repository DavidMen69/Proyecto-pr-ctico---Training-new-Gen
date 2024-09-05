package com.example.ProyectoCoolCorders.Services;

import com.example.ProyectoCoolCorders.Dto.OrderModelDto;
import com.example.ProyectoCoolCorders.Models.Ordermodel;

public interface OrderService {

    // Buscar Order por UUID
    Ordermodel getOrderByUuid(String uuid);

    // Crear Order
    void createOrder(Ordermodel order);

    // Actualizar Order
    boolean updateOrder(String uuid, OrderModelDto orderDto);

    // Eliminar Order
    boolean deleteOrderByUuid(String uuid);
}

