package com.example.ProyectoCoolCorders.Controllers;

import com.example.ProyectoCoolCorders.Exceptions.OrderNotFoundException;
import com.example.ProyectoCoolCorders.Exceptions.ProductNotFoundException;
import com.example.ProyectoCoolCorders.Models.Dto.OrderModelDto;
import com.example.ProyectoCoolCorders.Models.Dto.OrderResponseDto;
import com.example.ProyectoCoolCorders.Models.Entity.ClientModel;
import com.example.ProyectoCoolCorders.Models.Entity.Ordermodel;
import com.example.ProyectoCoolCorders.Models.Entity.ProductModel;
import com.example.ProyectoCoolCorders.Services.ClientService;
import com.example.ProyectoCoolCorders.Services.OrderService;
import com.example.ProyectoCoolCorders.Services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final ClientService clientService;

    @Autowired
    private final ProductService productService;


    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody OrderModelDto orderDto) {

        ClientModel client = clientService.getClientByDocument(orderDto.getClientDocument());
        if (client == null) {
            throw new OrderNotFoundException("Cliente con documento " + orderDto.getClientDocument() + " no encontrado");
        }

        ProductModel product = productService.getProductByuuid(orderDto.productUUID)
                .orElseThrow(() -> new ProductNotFoundException("Producto con UUID " + orderDto.productUUID + " no encontrado"));


        Ordermodel orderToSave = new Ordermodel();
        orderToSave.setClientDocument(client);
        orderToSave.setProductUUID(product);
        orderToSave.setQuantity(orderDto.getQuantity());
        orderToSave.setExtraInformation(orderDto.getExtraInformation());


        double productPrice = Double.parseDouble(String.valueOf(orderToSave.getProductUUID().getPrice()));
        double subTotal = productPrice * orderToSave.getQuantity();
        double taxPrice = subTotal*((double) 19 /100);
        double grandTotal = taxPrice+subTotal;

        orderToSave.setSubTotal(subTotal);
        orderToSave.setTax(taxPrice);
        orderToSave.setGrandTotal(grandTotal);

        Ordermodel savedOrder = orderService.createOrder(orderToSave);

        OrderResponseDto responseDto = new OrderResponseDto();

        responseDto.setUuid(savedOrder.getUuid());
        responseDto.setCreationDateTime(savedOrder.getCreationDateTime());
        responseDto.setClientDocument(savedOrder.getClientDocument().getDocument());
        responseDto.setProductUUID(savedOrder.getProductUUID().getUuid());
        responseDto.setQuantity(savedOrder.getQuantity());
        responseDto.setExtraInformation(savedOrder.getExtraInformation());
        responseDto.setSubTotal(savedOrder.getSubTotal());
        responseDto.setTax(savedOrder.getTax());
        responseDto.setGrandTotal(savedOrder.getGrandTotal());
        responseDto.setDelivered(savedOrder.isDelivered());
        responseDto.setDeliveredDate(savedOrder.getDeliveredDate());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    @GetMapping("/{uuid}")
    public ResponseEntity<Ordermodel> getOrderByuuid(@PathVariable String uuid){
        Ordermodel ordermodel = orderService.getOrderByUuid(uuid);
        return new ResponseEntity<>(ordermodel, HttpStatus.OK);
    }

    @PutMapping("/{uuid}/delivered/{timestamp}")
    public ResponseEntity<Object> updateOrder(@PathVariable String uuid, @PathVariable String timestamp) {


        LocalDateTime deliveredDate;
        try {
            deliveredDate = LocalDateTime.parse(timestamp);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>("Formato de fecha inválido", HttpStatus.BAD_REQUEST);
        }


        OrderModelDto orderDto = new OrderModelDto();
        orderDto.setDelivered(true);
        orderDto.setDeliveredDate(deliveredDate);

        try {

            Ordermodel updatedOrder = orderService.updateOrder(uuid, orderDto);

            if (updatedOrder != null) {

                OrderResponseDto responseDto = new OrderResponseDto();

                responseDto.setUuid(updatedOrder.getUuid());
                responseDto.setCreationDateTime(updatedOrder.getCreationDateTime());
                responseDto.setClientDocument(updatedOrder.getClientDocument().getDocument());
                responseDto.setProductUUID(updatedOrder.getProductUUID().getUuid());
                responseDto.setQuantity(updatedOrder.getQuantity());
                responseDto.setExtraInformation(updatedOrder.getExtraInformation());
                responseDto.setSubTotal(updatedOrder.getSubTotal());
                responseDto.setTax(updatedOrder.getTax());
                responseDto.setGrandTotal(updatedOrder.getGrandTotal());
                responseDto.setDelivered(updatedOrder.isDelivered());
                responseDto.setDeliveredDate(updatedOrder.getDeliveredDate());

                return new ResponseEntity<>(responseDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>("La orden no fue encontrada con los datos proporcionados", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la orden: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}


