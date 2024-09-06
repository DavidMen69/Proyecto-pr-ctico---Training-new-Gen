package com.example.ProyectoCoolCorders.Services.Impl;

import org.springframework.stereotype.Service;

import com.example.ProyectoCoolCorders.Models.Dto.ClientModelDto;
import com.example.ProyectoCoolCorders.Models.Entity.ClientModel;
import com.example.ProyectoCoolCorders.Repositories.ClientRepository;
import com.example.ProyectoCoolCorders.Services.ClientService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

        public final ClientRepository clientRepository;

        @Override
        public ClientModel getClientByDocument(String document) {
            return clientRepository.findByDocument(document).orElseThrow(() -> new RuntimeException("Document not found"));
        }

        @Override
        public void createClient(ClientModel client) {
            clientRepository.save(client);
        }

        @Override
        public boolean deleteClient(String document) {
            Optional<ClientModel> existingClient = clientRepository.findByDocument(document);
            if (existingClient.isPresent()){
                clientRepository.delete(existingClient.get());
                return true;
            }
            return false;
        }

        @Override
        public boolean updateClient(String document, ClientModelDto clientDTO) {
            Optional<ClientModel> existingClientOtp = clientRepository.findByDocument(document);

            if (existingClientOtp.isEmpty()){
                throw new RuntimeException("Client not found");
            }

            ClientModel existingClient = existingClientOtp.get();

            existingClient.setName(clientDTO.getName());
            existingClient.setEmail(clientDTO.getEmail());
            existingClient.setPhone(clientDTO.getPhone());
            existingClient.setDeliveryAddress(clientDTO.getDeliveryAddress());

            clientRepository.save(existingClient);

            return true;
    }
}
