package com.chandu.product.mapper;

import com.chandu.product.domain.Product;
import com.chandu.product.record.ProductRequest;
import com.chandu.product.record.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {


    public Product toProduct(ProductRequest request) {
        return new Product(
                request.id(),
                request.name(),
                request.description(),
                request.availableQuantity(),
                request.price(),
                request.category()
        );
    }

    public ProductResponse fromProduct(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory()
        );
    }
}
