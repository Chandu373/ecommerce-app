package com.chandu.notification.record;

import com.chandu.notification.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products ) {
}
