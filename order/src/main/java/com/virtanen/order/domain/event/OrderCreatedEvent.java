package com.virtanen.order.domain.event;

import com.virtanen.event.Event;
import com.virtanen.order.domain.model.CustomerDetails;
import com.virtanen.order.domain.model.PaymentDetails;
import com.virtanen.order.domain.model.ShippingDetails;
import com.virtanen.order.domain.model.ShoppingCart;

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
