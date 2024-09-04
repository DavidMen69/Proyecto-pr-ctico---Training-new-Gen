package com.example.ProyectoCoolCorders.Services.Impl;

import org.springframework.stereotype.Service;

import com.example.ProyectoCoolCorders.Models.Dto.ClientModelDto;
import com.example.ProyectoCoolCorders.Models.Entity.ClientModel;
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
    public boolean createClient(ClientModel client) {
        if (clientRepository.getReferenceByDocument(client.getDocument()) != null) {
            throw new RuntimeException("Cliente con número de documento ya existe");
        }
        clientRepository.save(client);
        return true;
    }

    @Override
    public boolean deleteClient(String document) {
        ClientModel isDeleteclient = clientRepository.getReferenceByDocument(document);
        clientRepository.delete(isDeleteclient);
        return true;
    }
    
    @Override
    public boolean updateClient(String document, ClientModelDto clientDTO) {
        ClientModel existingClient = clientRepository.getReferenceByDocument(document);
            
        existingClient.setName(clientDTO.getName());
        existingClient.setEmail(clientDTO.getEmail());
        existingClient.setPhone(clientDTO.getPhone());
        existingClient.setDeliveryAddress(clientDTO.getDeliveryAddress());
            
        clientRepository.save(existingClient);
        return true;
}

}
