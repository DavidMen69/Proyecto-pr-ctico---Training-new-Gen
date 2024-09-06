package com.example.ProyectoCoolCorders.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProyectoCoolCorders.Models.Entity.ProductModels;

@Repository
public interface ProductRepository extends JpaRepository <ProductModels, Long> {
    //Metodo para obtener El UUID
    Optional<ProductModels> findByUuid(String uuid);

    //Metodo para verificar si existe un producto con el mismo nombre fantasia
    boolean existsByFantasyName(String fantasyName);

    

}
