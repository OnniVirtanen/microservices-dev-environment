package com.virtanen.event.events;

import com.virtanen.event.Event;

public class PaymentRefundedEvent extends Event {

    private String paymentId;
    private String orderId;

    public PaymentRefundedEvent() {
        super("paymentRefunded");
    }

    public PaymentRefundedEvent(String paymentId, String orderId) {
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