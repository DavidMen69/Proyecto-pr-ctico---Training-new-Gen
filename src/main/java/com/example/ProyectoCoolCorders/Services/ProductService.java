package com.example.ProyectoCoolCorders.Services;

import com.example.ProyectoCoolCorders.Models.Dto.ProductModelDto;
import com.example.ProyectoCoolCorders.Models.Entity.ProductModel;

import java.util.Optional;

public interface ProductService {
    // buscar product 
    Optional<ProductModel> getProductByuuid(String uuid);
    
    //crear product
    ProductModel createProduct(ProductModel product);

    //Actualizar product
    boolean updateProduct(String uuid, ProductModelDto productDto);

    // Eliminar Product
    boolean deleteProductByUuid(String uuid);

}
