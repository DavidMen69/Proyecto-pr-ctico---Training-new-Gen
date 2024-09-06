package com.example.ProyectoCoolCorders.Services.Impl;

import com.example.ProyectoCoolCorders.Models.Dto.OrderModelDto;
import com.example.ProyectoCoolCorders.Exceptions.OrderNotFoundException;
import com.example.ProyectoCoolCorders.Models.Entity.Ordermodel;
import com.example.ProyectoCoolCorders.Repositories.OrderRepository;
import com.example.ProyectoCoolCorders.Services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    // Método para crear una nueva orden
    @Override
    public void createOrder(Ordermodel order) {
        // Generar UUID para la orden
        order.setUuid(UUID.randomUUID().toString());

        // Guardar la orden en el repositorio
        orderRepository.save(order);
    }

    // Método para obtener una orden por UUID
    @Override
    public Ordermodel getOrderByUuid(String uuid) {
        return orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    // Método para actualizar una orden
    @Override
    public boolean updateOrder(String uuid, OrderModelDto orderDto) {
        Optional<Ordermodel> existingOrderOpt = orderRepository.findByUuid(uuid);

        if (!existingOrderOpt.isPresent()) {
            throw new OrderNotFoundException("Order not found");
        }

        Ordermodel existingOrder = existingOrderOpt.get();
        boolean isUpdated = false;

        if (!existingOrder.getExtraInformation().equalsIgnoreCase(orderDto.getExtraInformation())) {
            existingOrder.setExtraInformation(orderDto.getExtraInformation());
            isUpdated = true;
        }

        if (existingOrder.getQuantity() != orderDto.getQuantity()) {
            existingOrder.setQuantity(orderDto.getQuantity());
            isUpdated = true;
        }

        // Agrega más campos de actualización si es necesario

        if (isUpdated) {
            orderRepository.save(existingOrder);
        }

        return isUpdated;
    }

    @Override
    public boolean deleteOrderByUuid(String uuid) {
        return false;
    }

}

