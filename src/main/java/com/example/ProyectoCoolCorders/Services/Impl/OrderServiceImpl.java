package com.example.ProyectoCoolCorders.Services.Impl;

import com.example.ProyectoCoolCorders.Exceptions.ProductNotFoundException;
import com.example.ProyectoCoolCorders.Models.Dto.OrderModelDto;
import com.example.ProyectoCoolCorders.Exceptions.OrderNotFoundException;
import com.example.ProyectoCoolCorders.Models.Entity.ClientModel;
import com.example.ProyectoCoolCorders.Models.Entity.Ordermodel;
import com.example.ProyectoCoolCorders.Models.Entity.ProductModel;
import com.example.ProyectoCoolCorders.Repositories.ClientRepository;
import com.example.ProyectoCoolCorders.Repositories.OrderRepository;
import com.example.ProyectoCoolCorders.Repositories.ProductRepository;
import com.example.ProyectoCoolCorders.Services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final ClientRepository clientRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final OrderRepository orderRepository;

    @Override
    public Ordermodel createOrder(Ordermodel order) {

        order.setUuid(UUID.randomUUID().toString());

        return orderRepository.save(order);

    }

    @Override
    public Ordermodel getOrderByUuid(String uuid) {
        return orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    @Override
    public Ordermodel updateOrder(String uuid, OrderModelDto orderDto) {

        Optional<Ordermodel> existingOrderOpt = orderRepository.findByUuid(uuid);
        if (existingOrderOpt.isEmpty()) {
            throw new OrderNotFoundException("Orden con UUID " + uuid + " no encontrada");
        }

        Ordermodel existingOrder = existingOrderOpt.get();

        if (orderDto.delivered){
            existingOrder.setDelivered(true);
        }

        if (orderDto.getDeliveredDate() != null) {
            existingOrder.setDeliveredDate(orderDto.getDeliveredDate());
        }

        return orderRepository.save(existingOrder);
    }

}

