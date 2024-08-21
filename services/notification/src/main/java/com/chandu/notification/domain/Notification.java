package com.chandu.notification.domain;

import com.chandu.notification.enums.NotificationType;
import com.chandu.notification.record.OrderConfirmation;
import com.chandu.notification.record.PaymentConfirmation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "NOTIFICATION")
public class Notification {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
