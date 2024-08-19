package com.chandu.payment.record;

import com.chandu.payment.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerName,
        String customerEmail
) {
}
