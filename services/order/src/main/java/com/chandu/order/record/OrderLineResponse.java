package com.chandu.order.record;

import java.math.BigDecimal;

public record OrderLineResponse(
        Integer id,
        double quantity
) {
}
