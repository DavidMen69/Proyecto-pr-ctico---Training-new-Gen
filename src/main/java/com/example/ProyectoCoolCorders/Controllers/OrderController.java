package com.example.ProyectoCoolCorders.Controllers;

import com.example.ProyectoCoolCorders.Models.Dto.OrderModelDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    // Simularemos una base de datos con una lista
    private List<OrderModelDto> orders = new ArrayList<>();

    // Obtener todas las órdenes
    @GetMapping
    public ResponseEntity<List<OrderModelDto>> getAllOrders() {
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Obtener una orden por UUID
    @GetMapping("/{uuid}")
    public ResponseEntity<OrderModelDto> getOrderById(@PathVariable String uuid) {
        return orders.stream()
                .filter(order -> order.getUuid().equals(uuid))
                .findFirst()
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear una nueva orden
    @PostMapping
    public ResponseEntity<OrderModelDto> createOrder(@RequestBody OrderModelDto orderModelDto) {
        orderModelDto.setUuid(UUID.randomUUID().toString()); // Asignar UUID único
        orders.add(orderModelDto);
        return new ResponseEntity<>(orderModelDto, HttpStatus.CREATED);
    }

    // Actualizar una orden existente
    @PutMapping("/{uuid}")
    public ResponseEntity<OrderModelDto> updateOrder(@PathVariable String uuid, @RequestBody OrderModelDto updatedOrder) {
        for (int i = 0; i < orders.size(); i++) {
            OrderModelDto order = orders.get(i);
            if (order.getUuid().equals(uuid)) {
                updatedOrder.setUuid(uuid);
                orders.set(i, updatedOrder);
                return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}


