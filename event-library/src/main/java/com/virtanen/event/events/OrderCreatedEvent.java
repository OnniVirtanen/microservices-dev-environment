package com.virtanen.event.events;

import com.virtanen.event.Event;
import com.virtanen.event.events.dto.CustomerDetails;
import com.virtanen.event.events.dto.PaymentDetails;
import com.virtanen.event.events.dto.ShippingDetails;
import com.virtanen.event.events.dto.ShoppingCart;

public class OrderCreatedEvent extends Event {

    private String orderId;
    private ShoppingCart cart;
    private PaymentDetails payment;
    private ShippingDetails shipping;
    private CustomerDetails customerDetails;

    public OrderCreatedEvent() {
        super("orderCreated");
    }

    public OrderCreatedEvent(String orderId, ShoppingCart cart, PaymentDetails payment,
                             ShippingDetails shipping, CustomerDetails customerDetails) {
        this();
        this.orderId = orderId;
        this.cart = cart;
        this.payment = payment;
        this.shipping = shipping;
        this.customerDetails = customerDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public ShoppingCart getCart() {
        return cart;
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