package com.chandu.notification.service;

import com.chandu.notification.domain.Notification;
import com.chandu.notification.email.EmailService;
import com.chandu.notification.enums.NotificationType;
import com.chandu.notification.record.OrderConfirmation;
import com.chandu.notification.record.PaymentConfirmation;
import com.chandu.notification.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void  consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("consuming the message from payment-topic Topic :: {} ", paymentConfirmation);
        notificationRepository.save(Notification.builder()
                        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                .build()
        );
        // TODO sent email
        emailService.sendPaymentSuccessEmail(paymentConfirmation.customerEmail(),
                paymentConfirmation.customerName(),paymentConfirmation.amount(), paymentConfirmation.orderReference());

    }

    @KafkaListener(topics = "order-topic")
    public void  consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("consuming the message from order-topic Topic :: {}",orderConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                .build()
        );
        //  TODO sent email
        emailService.sendOrderConfirmationEmail(orderConfirmation.customer().email(),orderConfirmation.customer().name(),
                orderConfirmation.amount(), orderConfirmation.orderReference(),orderConfirmation.products());
    }


}
