package com.virtanen.shipping.demo.domain.event;

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
