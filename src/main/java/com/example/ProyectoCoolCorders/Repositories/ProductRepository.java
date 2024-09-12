package com.example.ProyectoCoolCorders.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ProyectoCoolCorders.Models.Entity.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository <ProductModel, Long> {

    ProductModel getReferenceByUuid(String Uuid);

    Optional<ProductModel> findByUuid(String uuid);

    boolean existsByFantasyName(String fantasyName);

    //Metodo para buscar productos por nombre de fantasia usando LIKE
    //@Query("SELECT p FROM ProductModel p WHERE LOWER(p.fantasyName) LIKE LOWER(CONCAT('%', :query, '%'))ORDER BY p.fantasyName ASC")
    //List<ProductModel> searchByFantasyName(@Param("query") String query);
    List<ProductModel> findByFantasyNameContainingOrderByFantasyNameAsc (String query);


}
