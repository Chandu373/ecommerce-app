package com.chandu.payment.mapper;

import com.chandu.payment.domain.Payment;
import com.chandu.payment.record.PaymentRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(@Valid PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .build();
    }
}
