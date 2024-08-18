package com.chandu.product.record;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "product Id is required")
        Integer productId,
        @NotNull(message = "product quantity is required")
        double  quantity
) {
}
