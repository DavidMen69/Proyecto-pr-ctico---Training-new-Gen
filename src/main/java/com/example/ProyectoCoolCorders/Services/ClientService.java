package com.example.ProyectoCoolCorders.Services;

import com.example.ProyectoCoolCorders.Models.Dto.ClientModelDto;
import com.example.ProyectoCoolCorders.Models.Entity.ClientModel;

public interface ClientService {

    ClientModel getClientByDocument(String document);

    boolean createClient(ClientModel document);

    boolean deleteClient(String  document);

    boolean updateClient(String document, ClientModelDto clientDTO);
    
}
