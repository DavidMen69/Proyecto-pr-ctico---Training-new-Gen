package com.example.ProyectoCoolCorders.Services;

import com.example.ProyectoCoolCorders.Models.Dto.ProductModelDto;
import com.example.ProyectoCoolCorders.Models.Entity.ProductModel;

public interface ProductService {
    // buscar product 
    ProductModel getProductByuuid(String uuid);
    
    //crear product
    void createProduct(ProductModel product);

    //Actualizar product
    boolean updateProduct(String uuid, ProductModelDto productDto);

    // Eliminar Product
    boolean deleteProductByUuid(String uuid);

}
