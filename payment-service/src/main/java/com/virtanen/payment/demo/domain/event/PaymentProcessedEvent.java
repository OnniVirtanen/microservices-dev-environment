package com.virtanen.payment.demo.domain.event;

import com.virtanen.event.Event;

public class PaymentProcessedEvent extends Event {

    private String paymentId;
    private String orderId;

    public PaymentProcessedEvent() {
        super("paymentProcessed");
    }

    public PaymentProcessedEvent(String paymentId, String orderId) {
        this();
        this.paymentId = paymentId;
        this.orderId = orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

}
