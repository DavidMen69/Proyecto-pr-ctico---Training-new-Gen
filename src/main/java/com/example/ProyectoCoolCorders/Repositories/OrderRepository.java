package com.example.ProyectoCoolCorders.Repositories;

import com.example.ProyectoCoolCorders.Models.Ordermodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Ordermodel, Long> {

    // Método para buscar una orden por UUID
    Optional<Ordermodel> findByUuid(String uuid);

    // Método para eliminar una orden por UUID (opcional)
    void deleteByUuid(String uuid);

    // Puedes agregar más métodos personalizados si es necesario
}

