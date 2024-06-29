package com.virtanen.payment.demo.domain.event;

import com.virtanen.event.Event;
import com.virtanen.payment.demo.domain.event.dto.CustomerDetails;
import com.virtanen.payment.demo.domain.event.dto.PaymentDetails;
import com.virtanen.payment.demo.domain.event.dto.ShippingDetails;

public class ReserveItemsEvent extends Event {

    private String orderId;
    private PaymentDetails payment;
    private ShippingDetails shipping;
    private CustomerDetails customerDetails;

    public ReserveItemsEvent() {
        super("itemsReserved");
    }

    public ReserveItemsEvent(String orderId, PaymentDetails payment,
                             ShippingDetails shipping, CustomerDetails customerDetails) {
        this();
        this.orderId = orderId;
        this.payment = payment;
        this.shipping = shipping;
        this.customerDetails = customerDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public PaymentDetails getPayment() {
        return payment;
    }

    public ShippingDetails getShipping() {
        return shipping;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

}
