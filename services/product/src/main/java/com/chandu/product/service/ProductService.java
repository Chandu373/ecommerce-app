package com.chandu.product.service;

import com.chandu.product.domain.Product;
import com.chandu.product.mapper.ProductMapper;
import com.chandu.product.record.ProductRequest;
import com.chandu.product.record.ProductResponse;
import com.chandu.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;


    public Product save(ProductRequest productRequest) {
        Product product = null;
        if(productRequest== null){
            return  null;
        }
        product = productMapper.toProduct(productRequest);
        return product;
    }

    public void deleteProduct(Integer productId) {
       productRepository.deleteById(productId);
    }


    public List<ProductResponse> findAllProducts() {
       return  productRepository. findAll().stream()
               .map(productMapper::fromProduct)
               .collect(Collectors.toList());
    }

    public Product findProductById(Integer productId) {
        return  productRepository. findById(productId).orElseThrow(() -> new RuntimeException("Product Not  Found"));
    }
}
