package com.example.ProyectoCoolCorders.Services.Impl;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.ProyectoCoolCorders.Exceptions.ProductAlreadyExistsException;
import com.example.ProyectoCoolCorders.Models.Dto.ProductModelDto;
import com.example.ProyectoCoolCorders.Models.Entity.ProductModel;
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
    public ProductModel createProduct(ProductModel product) {

        if(productRepository.existsByFantasyName(product.getFantasyName())){
            throw new ProductAlreadyExistsException("Producto Con Nombre Fantasia Ya Existe");
        }

        product.setUuid(UUID.randomUUID().toString());

        product.setFantasyName(product.getFantasyName().toUpperCase());
        product.setCategory(product.getCategory().toUpperCase());

        ProductModel saveproduct = productRepository.save(product);
        return saveproduct;
    }

    // Metodo para Obtener Producto Por UUID
    @Override
    public Optional<ProductModel> getProductByuuid(String uuid) {
        return productRepository.findByUuid(uuid);
    }


    //Metodo Para Actualizar Producto

    @Override 
    public boolean updateProduct(String uuid, ProductModelDto productDto){
        Optional<ProductModel> existingProductOpt = productRepository.findByUuid(uuid);

        if(existingProductOpt.isEmpty()){
            throw new RuntimeException("Product not found");
        }

        ProductModel existingProduct = existingProductOpt.get();

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
        Optional<ProductModel> existingProductOpt = productRepository.findByUuid(uuid);

        if(existingProductOpt.isPresent()){
            productRepository.delete(existingProductOpt.get());
            return true;
        }
        return false;
    }

    @Override
    public List<ProductModel> searchProductsByFantasyName(String query){
        //ist<ProductModel> products = productRepository.searchByFantasyName(query);

        return productRepository.findByFantasyNameContainingOrderByFantasyNameAsc(query);
    }


}
