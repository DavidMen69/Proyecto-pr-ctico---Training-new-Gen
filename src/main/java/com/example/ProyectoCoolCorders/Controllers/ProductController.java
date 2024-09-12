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
import com.example.ProyectoCoolCorders.Models.Entity.ProductModel;
import com.example.ProyectoCoolCorders.Services.ProductService;

import lombok.RequiredArgsConstructor;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ProductModelDto product){

        ProductModel productToSave = new ProductModel();

        productToSave.setFantasyName(product.fantasyName);
        productToSave.setCategory(product.category);
        productToSave.setPrice(product.price);
        productToSave.setDescription(product.description);
        productToSave.setAvailable(product.available);
        ProductModel saveProduct = productService.createProduct(productToSave);

        return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<String> handleProductAlreadyExistsException(ProductAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }


    @GetMapping("/{uuid}")
    public ResponseEntity<Object> getProductByuuid(@PathVariable String uuid){
        Optional<ProductModel> product = productService.getProductByuuid(uuid);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PutMapping("/{uuid}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable String uuid, @RequestBody ProductModelDto productDto){
        boolean  isUpdate = productService.updateProduct(uuid, productDto);
        if(isUpdate){
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String uuid){
        try {
            boolean isDeleted = productService.deleteProductByUuid(uuid);
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
