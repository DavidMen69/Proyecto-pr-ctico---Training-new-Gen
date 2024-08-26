package com.example.ProyectoCoolCorders.Services.Impl;

import org.springframework.stereotype.Service;

import com.example.ProyectoCoolCorders.Dto.ClientModelDto;
import com.example.ProyectoCoolCorders.Models.ClientModel;
import com.example.ProyectoCoolCorders.Repositories.ClientRepository;
import com.example.ProyectoCoolCorders.Services.ClientService;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    public final ClientRepository clientRepository;

    @Override
    public ClientModel getClientByDocument(String document) {
        return clientRepository.getReferenceByDocument(document);
    }

    @Override
    public void createClient(ClientModel document) {
        clientRepository.save(document);
    }

    @Override
    public void deleteClient(String document) {
        ClientModel client = clientRepository.getReferenceByDocument(document);
    
        if (client != null) {
            clientRepository.delete(client);
        } else {
            throw new RuntimeException("Cliente no encontrado con documento: " + document);
        }
    }
    
    @Override
    public void updateClient(String document, ClientModelDto clientDTO) {
        ClientModel existingClient = clientRepository.getReferenceByDocument(document);
        
        if (existingClient != null) {
            existingClient.setName(clientDTO.getName());
            existingClient.setEmail(clientDTO.getEmail());
            existingClient.setPhone(clientDTO.getPhone());
            existingClient.setDeliveryAddress(clientDTO.getDeliveryAddress());
            
            clientRepository.save(existingClient);
        } else {
            throw new RuntimeException("Cliente no encontrado con documento: " + document);
        }
}

}
