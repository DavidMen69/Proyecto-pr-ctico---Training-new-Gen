package com.example.ProyectoCoolCorders.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoCoolCorders.Dto.ClientModelDto;
import com.example.ProyectoCoolCorders.Models.ClientModel;
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

    @GetMapping("/{document}")
    public ResponseEntity<ClientModel> getClientByDocument(@PathVariable String document) {
        return ResponseEntity.ok(clientService.getClientByDocument(document));
    }

    @PostMapping("/createClient")
    public ResponseEntity<String> createClient(@RequestBody ClientModelDto client){

        ClientModel clientToSave = new ClientModel();

        clientToSave.setDocument(client.document);
        clientToSave.setName(client.name);
        clientToSave.setEmail(client.email);
        clientToSave.setPhone(client.phone);
        clientToSave.setDeliveryAddress(client.deliveryAddress);
        clientService.createClient(clientToSave);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Client created Sucessfully");
    }

    @PutMapping("/{document}")
    public ResponseEntity<String> updateClient(@PathVariable String document, @RequestBody ClientModelDto clientDTO) {
        try {
            clientService.updateClient(document, clientDTO);
            return ResponseEntity.ok("Cliente actualizado exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{document}")
    public ResponseEntity<String> destroy(@PathVariable String document) {
        try {
            clientService.deleteClient(document);
            return new ResponseEntity<>("Cliente eliminador exitosamente.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
