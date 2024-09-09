package com.example.ProyectoCoolCorders.Services;

import com.example.ProyectoCoolCorders.Models.Dto.OrderModelDto;
import com.example.ProyectoCoolCorders.Models.Entity.ClientModel;
import com.example.ProyectoCoolCorders.Models.Entity.Ordermodel;

public interface OrderService {


    Ordermodel getOrderByUuid(String uuid);

    Ordermodel createOrder(Ordermodel order);

    Ordermodel updateOrder(String uuid, OrderModelDto orderDto);

}

