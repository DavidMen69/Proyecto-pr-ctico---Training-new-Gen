package com.example.ProyectoCoolCorders.Services.Impl;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.ProyectoCoolCorders.Exceptions.ProductAlreadyExistsException;
import com.example.ProyectoCoolCorders.Models.Dto.ProductModelDto;
import com.example.ProyectoCoolCorders.Models.Entity.ProductModels;
import com.example.ProyectoCoolCorders.Repositories.ProductRepository;
import com.example.ProyectoCoolCorders.Services.ProductService;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    
    private final ProductRepository productRepository;

    //Metodo Crear Producto
    @Override
    public void createProduct(ProductModels product) {

        if(productRepository.existsByFantasyName(product.getFantasyName())){
            throw new ProductAlreadyExistsException("Producto Con Nombre Fantasia Ya Existe");
        }

        // Generar UUID
        product.setUuid(UUID.randomUUID().toString());

        // Convertir El Nombre De Fantasia A Masyusculas
        product.setFantasyName(product.getFantasyName().toUpperCase());
        product.setCategory(product.getCategory().toUpperCase());

        // Guardar Producto 
        productRepository.save(product);
    }

    // Metodo para Obtener Producto Por UUID
    @Override
    public ProductModels getProductByuuid(String uuid) {
        return productRepository.findByUuid(uuid).orElseThrow(() -> new RuntimeException("Product not found"));
    }


    //Metodo Para Actualizar Producto

    @Override 
    public boolean updateProduct(String uuid, ProductModelDto productDto){
        Optional<ProductModels> existingProductOpt = productRepository.findByUuid(uuid);

        if(!existingProductOpt.isPresent()){
            throw new RuntimeException("Product not found");
        }

        ProductModels existingProduct = existingProductOpt.get();

        if (!existingProduct.getFantasyName().equalsIgnoreCase(productDto.getFantasyName()) && 
            productRepository.existsByFantasyName(productDto.getFantasyName().toUpperCase())) {
            throw new ProductAlreadyExistsException("Producto con nombre fantasía ya existe");
        }

        boolean isUpdated = false;

        if (!existingProduct.getFantasyName().equalsIgnoreCase(productDto.getFantasyName())) {
            existingProduct.setFantasyName(productDto.getFantasyName().toUpperCase());
            isUpdated = true;
        }

        if (!existingProduct.getCategory().equalsIgnoreCase(productDto.getCategory())) {
            existingProduct.setCategory(productDto.getCategory().toUpperCase());
            isUpdated = true;
        }

        if (!existingProduct.getDescription().equalsIgnoreCase(productDto.getDescription())) {
            existingProduct.setDescription(productDto.getDescription());
            isUpdated = true;
        } 

        if (existingProduct.getPrice() != productDto.getPrice()) {
            existingProduct.setPrice(productDto.getPrice());
            isUpdated = true;
        }
        if (existingProduct.isAvailable() != productDto.isAvailable()) {
            existingProduct.setAvailable(productDto.isAvailable());
            isUpdated = true;
        }

        if (isUpdated) {
            productRepository.save(existingProduct);
        }

        return isUpdated;
    }

    // Servicio Delete Product By Uuid
    @Override
    public boolean deleteProductByUuid(String uuid) {
        Optional<ProductModels> existingProductOpt = productRepository.findByUuid(uuid);

        if(existingProductOpt.isPresent()){
            productRepository.delete(existingProductOpt.get());
            return true;
        }
        return false;
    }


}
