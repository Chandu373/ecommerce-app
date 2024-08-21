package com.chandu.product.controller;

import com.chandu.product.domain.Product;
import com.chandu.product.record.ProductPurchaseRequest;
import com.chandu.product.record.ProductPurchaseResponse;
import com.chandu.product.record.ProductRequest;
import com.chandu.product.record.ProductResponse;
import com.chandu.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService ;

    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody ProductRequest request){
       return ResponseEntity.ok(productService.save(request));
    }

    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> request){
        return ResponseEntity.ok(productService.purchaseProducts(request));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>>  findAll(){
        return ResponseEntity.ok(productService.findAllProducts());
    }


    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findByID(@PathVariable(value = "product-id") Integer productId){
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    @DeleteMapping("/delete/{product-id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "product-id") Integer productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("success");
    }
}
