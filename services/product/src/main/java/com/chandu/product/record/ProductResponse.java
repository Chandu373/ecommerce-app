package com.chandu.product.record;

import com.chandu.product.domain.Category;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Category category
) {
}
