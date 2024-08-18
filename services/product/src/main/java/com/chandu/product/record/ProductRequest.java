package com.chandu.product.record;

import com.chandu.product.domain.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "product name is required")
        String name,
        @NotNull(message = "product name is description")
        String description,
        @Positive(message = "availableQuantity should be positive")
        double availableQuantity,
        @Positive(message = "price should be positive")
        BigDecimal price,
        @NotNull(message = "product name is category")
        Integer categoryId)
{

}
