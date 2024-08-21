package com.chandu.notification.record;

import com.chandu.notification.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerName,
        String customerEmail) {
}
