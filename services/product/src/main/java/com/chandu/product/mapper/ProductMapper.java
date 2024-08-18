package com.chandu.product.mapper;

import com.chandu.product.domain.Category;
import com.chandu.product.domain.Product;
import com.chandu.product.record.ProductPurchaseResponse;
import com.chandu.product.record.ProductRequest;
import com.chandu.product.record.ProductResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {


    public Product toProduct(ProductRequest request) {
        if(request == null){
            return  null;
        }
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(
                        Category.builder()
                                .id(request.categoryId())
                                .build())
                .build();
    }

    public ProductResponse fromProduct(Product product) {
        if(product == null){
            return  null;
        }
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, @NotNull(message = "product quantity is required") double quantity) {
            return new  ProductPurchaseResponse(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    quantity
                    );
    }
}
