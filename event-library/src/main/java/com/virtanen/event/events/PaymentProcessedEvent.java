package com.virtanen.event.events;

import com.virtanen.event.Event;
import com.virtanen.event.events.dto.CustomerDetails;
import com.virtanen.event.events.dto.ShippingDetails;

public class PaymentProcessedEvent extends Event {

    private String paymentId;
    private String orderId;
    private ShippingDetails shipping;
    private CustomerDetails customerDetails;

    public PaymentProcessedEvent() {
        super("paymentProcessed");
    }

    public PaymentProcessedEvent(String paymentId, String orderId, ShippingDetails shipping,
                                 CustomerDetails customerDetails) {
        this();
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.shipping = shipping;
        this.customerDetails = customerDetails;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public ShippingDetails getShipping() {
        return shipping;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

}