package com.example.ProyectoCoolCorders.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProyectoCoolCorders.Models.Entity.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository <ProductModel, Long> {

    ProductModel getReferenceByUuid(String Uuid);

    Optional<ProductModel> findByUuid(String uuid);

    boolean existsByFantasyName(String fantasyName);

}
