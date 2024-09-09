package com.example.ProyectoCoolCorders.Repositories;

import com.example.ProyectoCoolCorders.Models.Entity.Ordermodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProyectoCoolCorders.Models.Entity.Ordermodel;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Ordermodel, Long> {

    Optional<Ordermodel> findByUuid(String uuid);

    void deleteByUuid(String uuid);

}

