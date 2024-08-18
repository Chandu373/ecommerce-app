package com.chandu.product.record;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
