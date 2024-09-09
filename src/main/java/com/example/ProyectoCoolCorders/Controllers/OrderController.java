package com.example.ProyectoCoolCorders.Controllers;

import com.example.ProyectoCoolCorders.Exceptions.OrderNotFoundException;
import com.example.ProyectoCoolCorders.Exceptions.ProductNotFoundException;
import com.example.ProyectoCoolCorders.Models.Dto.OrderModelDto;
import com.example.ProyectoCoolCorders.Models.Dto.OrderResponseDto;
import com.example.ProyectoCoolCorders.Models.Entity.ClientModel;
import com.example.ProyectoCoolCorders.Models.Entity.Ordermodel;
import com.example.ProyectoCoolCorders.Models.Entity.ProductModel;
import com.example.ProyectoCoolCorders.Repositories.ClientRepository;
import com.example.ProyectoCoolCorders.Repositories.ProductRepository;
import com.example.ProyectoCoolCorders.Services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final ClientRepository clientRepository;

    @Autowired
    private final ProductRepository productRepository;


    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody OrderModelDto orderDto) {

        ClientModel client = clientRepository.findByDocument(orderDto.getClientDocument())
                .orElseThrow(() -> new OrderNotFoundException("Cliente con documento " + orderDto.getClientDocument() + " no encontrado"));


        ProductModel product = productRepository.findByUuid(orderDto.getProductUuid())
                .orElseThrow(() -> new ProductNotFoundException("Producto con UUID " + orderDto.getProductUuid() + " no encontrado"));


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
    public ResponseEntity<Object> updateOrder(
            @PathVariable String uuid,
            @PathVariable String timestamp) {


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

                Map<String, Object> response = new HashMap<>();
                response.put("uuid", updatedOrder.getUuid());
                response.put("creationDateTime", updatedOrder.getCreationDateTime().toString());
                response.put("clientDocument", updatedOrder.getClientDocument().getDocument());
                response.put("productUUID", updatedOrder.getProductUUID().getUuid());
                response.put("quantity", updatedOrder.getQuantity());
                response.put("extraInformation", updatedOrder.getExtraInformation());


                double productPrice;
                try {
                    productPrice = Double.parseDouble(String.valueOf(updatedOrder.getProductUUID().getPrice()));
                } catch (NumberFormatException e) {
                    return new ResponseEntity<>("Precio del producto inválido", HttpStatus.INTERNAL_SERVER_ERROR);
                }
                double subTotal = productPrice * updatedOrder.getQuantity();
                response.put("subTotal", subTotal);

                response.put("tax", updatedOrder.getTax());
                response.put("grandTotal", updatedOrder.getGrandTotal());
                response.put("delivered", updatedOrder.isDelivered());
                response.put("deliveredDate", updatedOrder.getDeliveredDate() != null ? updatedOrder.getDeliveredDate().toString() : null);

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("La orden no fue encontrada con los datos proporcionados", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la orden: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}


