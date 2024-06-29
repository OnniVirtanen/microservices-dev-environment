package com.onnivirtanen.inventory.domain.event;

import com.onnivirtanen.inventory.domain.event.dto.CustomerDetails;
import com.onnivirtanen.inventory.domain.event.dto.PaymentDetails;
import com.onnivirtanen.inventory.domain.event.dto.ShippingDetails;
import com.onnivirtanen.inventory.domain.event.dto.ShoppingCart;
import com.virtanen.event.Event;

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
