package com.example.ProyectoCoolCorders.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoCoolCorders.Models.Dto.ClientModelDto;
import com.example.ProyectoCoolCorders.Models.Entity.ClientModel;
import com.example.ProyectoCoolCorders.Services.ClientService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody ClientModelDto client){

        ClientModel clientToSave = new ClientModel();
            
        clientToSave.setDocument(client.document);
        clientToSave.setName(client.name);
        clientToSave.setEmail(client.email);
        clientToSave.setPhone(client.phone);
        clientToSave.setDeliveryAddress(client.deliveryAddress);
        clientService.createClient(clientToSave);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Client created successfully");

    }

    @GetMapping("/{document}")
    public ResponseEntity<ClientModel> getClientByDocument(@PathVariable String document) {
        ClientModel clientModel = clientService.getClientByDocument(document);
        return new ResponseEntity<>(clientModel, HttpStatus.OK);
    }

    @PutMapping("/{document}")
    public ResponseEntity<String> updateClient(@PathVariable String document, @RequestBody ClientModelDto clientDTO) {
        try {
            boolean updated = clientService.updateClient(document, clientDTO);
            if (updated) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>("El cliente ya existe o los datos son inconsistentes", HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar cliente: ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{document}")
    public ResponseEntity<String> destroy(@PathVariable String document) {
        try {
            boolean delete = clientService.deleteClient(document);
            if (delete) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(" No existe cliente con el número de documento suministrado", HttpStatus.NOT_FOUND);
            }
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Formato de documento inválido",HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>("Error general del servidor" + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 
