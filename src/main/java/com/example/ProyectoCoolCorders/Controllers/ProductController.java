package com.example.ProyectoCoolCorders.Controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoCoolCorders.Exceptions.ProductAlreadyExistsException;
import com.example.ProyectoCoolCorders.Models.Dto.ProductModelDto;
import com.example.ProyectoCoolCorders.Models.Entity.ProductModels;
import com.example.ProyectoCoolCorders.Services.ProducService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProducService producService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductModelDto product){

        ProductModels productToSave = new ProductModels();

        productToSave.setFantasyName(product.fantasyName);
        productToSave.setCategory(product.category);
        productToSave.setPrice(product.price);
        productToSave.setDescription(product.description);
        productToSave.setAvailable(product.available);
        producService.createProduct(productToSave);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Product created successfully");
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<String> handleProductAlreadyExistsException(ProductAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }


    @GetMapping("/{uuid}")
    public ResponseEntity<ProductModels> getProductByuuid(@PathVariable String uuid){
        ProductModels product = producService.getProductByuuid(uuid);
        return ResponseEntity.ok(product);
    }


    @PutMapping("/{uuid}")
    public ResponseEntity<ProductModels> updateProduct(@PathVariable String uuid, @RequestBody ProductModelDto productDto){
        boolean  isUpdate = producService.updateProduct(uuid, productDto);
        if(isUpdate){
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String uuid){
        try {
            boolean isDeleted = producService.deleteProductByUuid(uuid);
            if(isDeleted){
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
