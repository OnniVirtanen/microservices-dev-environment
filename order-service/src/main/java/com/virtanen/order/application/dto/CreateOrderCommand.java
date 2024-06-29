package com.virtanen.order.application.dto;

import com.virtanen.event.events.dto.CustomerDetails;
import com.virtanen.event.events.dto.PaymentDetails;
import com.virtanen.event.events.dto.ShippingDetails;
import com.virtanen.event.events.dto.ShoppingCart;

public class CreateOrderCommand {

    private ShoppingCart cart;
    private PaymentDetails payment;
    private ShippingDetails shipping;
    private CustomerDetails customerDetails;

    public CreateOrderCommand(ShoppingCart cart, PaymentDetails payment, ShippingDetails shipping,
                              CustomerDetails customerDetails) {
        this.cart = cart;
        this.payment = payment;
        this.shipping = shipping;
        this.customerDetails = customerDetails;
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
