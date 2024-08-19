package com.chandu.payment.service;

import com.chandu.payment.domain.Payment;
import com.chandu.payment.mapper.PaymentMapper;
import com.chandu.payment.notification.NotificationProducer;
import com.chandu.payment.record.PaymentNotificationRequest;
import com.chandu.payment.record.PaymentRequest;
import com.chandu.payment.repository.PaymentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private NotificationProducer notificationProducer;

    public Integer createPayment(@Valid PaymentRequest request) {
        Payment payment = paymentRepository.save(paymentMapper.toPayment(request));
        // sendingNotification
        sendNotification(request);

        return  payment.getId();

    }

    private void sendNotification(@Valid PaymentRequest request) {
        notificationProducer.sendNotification(new PaymentNotificationRequest(
                request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().name(),
                request.customer().email()
        ));
    }


}
