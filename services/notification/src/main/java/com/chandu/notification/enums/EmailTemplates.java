package com.chandu.notification.enums;

import lombok.Getter;

public enum EmailTemplates {

     PAYMENT_CONFIRMATION("payment-confirmation.html","Payment  successfully process"),
     ORDER_CONFIRMATION("order-confirmation.html","Order Confirmation");

     @Getter
     private String template;
     @Getter
     private String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
