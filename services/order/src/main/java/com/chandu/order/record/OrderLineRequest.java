package com.chandu.order.record;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        @NotNull(message = "product is mandatory")
        Integer productId,
        @Positive(message = "Quantity  is mandatory")
        double quantity
) {
}
