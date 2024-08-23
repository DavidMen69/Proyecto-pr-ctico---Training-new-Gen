package com.example.ProyectoCoolCorders.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ProyectoCoolCorders.Models.ClientModel;
import com.example.ProyectoCoolCorders.Services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{document}")
    public ResponseEntity<ClientModel> getClientByDocument(@PathVariable String document) {
        return ResponseEntity.ok(clientService.getClientByDocument(document));
    }
}
