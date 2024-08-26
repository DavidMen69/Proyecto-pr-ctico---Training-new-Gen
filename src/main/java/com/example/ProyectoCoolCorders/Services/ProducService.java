package com.example.ProyectoCoolCorders.Services;

import com.example.ProyectoCoolCorders.Dto.ProductModelDto;
import com.example.ProyectoCoolCorders.Models.ProductModels;

public interface ProducService {
    // buscar product 
    ProductModels getProductByuuid(String uuid);
    
    //crear product
    void createProduct(ProductModels product);

    //Actualizar product
    boolean updateProduct(String uuid, ProductModelDto productDto);

    // Eliminar Product
    boolean deleteProductByUuid(String uuid);

}
