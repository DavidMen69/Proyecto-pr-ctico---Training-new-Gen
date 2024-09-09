package com.example.ProyectoCoolCorders.Services;

import com.example.ProyectoCoolCorders.Models.Dto.ClientModelDto;
import com.example.ProyectoCoolCorders.Models.Entity.ClientModel;

public interface ClientService {

    ClientModel getClientByDocument(String document);

    ClientModel createClient(ClientModel document);

    ClientModel updateClient(String document, ClientModelDto clientDTO);

    Boolean deleteClient(String  document);


}
