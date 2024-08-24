package com.example.ProyectoCoolCorders.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoCoolCorders.Models.ClientModel;
import com.example.ProyectoCoolCorders.Models.Dto.ClientModelDto;
import com.example.ProyectoCoolCorders.Services.ClientService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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
    
}
