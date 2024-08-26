package com.example.ProyectoCoolCorders.Services;

import com.example.ProyectoCoolCorders.Dto.ClientModelDto;
import com.example.ProyectoCoolCorders.Models.ClientModel;

public interface ClientService {

    ClientModel getClientByDocument(String document);

    void createClient(ClientModel document);

    void deleteClient(String  document);

    void updateClient(String document, ClientModelDto clientDTO);
    
}
