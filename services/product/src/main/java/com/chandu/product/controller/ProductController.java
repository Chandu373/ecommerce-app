package com.chandu.product.controller;

import com.chandu.product.domain.Product;
import com.chandu.product.record.ProductRequest;
import com.chandu.product.record.ProductResponse;
import com.chandu.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/product")
public class ProductController {

    private ProductService productService ;

    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody ProductRequest productRequest){
       return ResponseEntity.ok(productService.save(productRequest));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>>  findAll(){
        return ResponseEntity.ok(productService.findAllProducts());
    }


    @GetMapping("/find/{product-id}")
    public ResponseEntity<Product> findByID(@PathVariable(value = "product-id") Integer productId){
        if(productId > 0){
            return ResponseEntity.ok(productService.findProductById(productId));
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{product-id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "product-id") Integer productId){
        if(productId > 0){
            productService.deleteProduct(productId);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
