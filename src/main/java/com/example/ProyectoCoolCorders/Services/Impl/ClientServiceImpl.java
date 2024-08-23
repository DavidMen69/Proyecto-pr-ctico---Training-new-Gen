package com.example.ProyectoCoolCorders.Services.Impl;

import org.springframework.stereotype.Service;
import com.example.ProyectoCoolCorders.Models.ClientModel;
import com.example.ProyectoCoolCorders.Repositories.ClientRepository;
import com.example.ProyectoCoolCorders.Services.ClientService;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    @Override
    public ClientModel getClientByDocument(String document) {
        return clientRepository.getReferenceByDocument(document);
    }

    @Override
    public void createClient(ClientModel document) {
        clientRepository.save(document);
    }

    @Override
    public ClientModel getClientByid(Long id) {
        return clientRepository.getReferenceById(id);
    }
}
