package com.virtanen.shipping.demo.domain.event;

import com.virtanen.shipping.demo.domain.util.EventIdGenerator;

public class PaymentProcessedEvent extends Event {

    private String paymentId;
    private String orderId;

    public PaymentProcessedEvent() {
    }

    public PaymentProcessedEvent(String paymentId, String orderId) {
        super(EventIdGenerator.generateId(), "_paymentProcessed");
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
