package com.example.ProyectoCoolCorders.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ProyectoCoolCorders.Models.ClientModel;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long>{


    ClientModel getReferenceByDocument(String document);

    
}
