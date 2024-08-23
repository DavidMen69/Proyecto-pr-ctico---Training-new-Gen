package com.example.ProyectoCoolCorders.Services;

import com.example.ProyectoCoolCorders.Models.ClientModel;

public interface ClientService {

    ClientModel getClientByid(Long id);

    ClientModel getClientByDocument(String document);

    void createClient(ClientModel document);
}
