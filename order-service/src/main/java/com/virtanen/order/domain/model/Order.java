package com.virtanen.order.domain.model;

import com.virtanen.event.events.dto.CustomerDetails;
import com.virtanen.event.events.dto.PaymentDetails;
import com.virtanen.event.events.dto.ShippingDetails;
import com.virtanen.event.events.dto.ShoppingCart;
import org.springframework.data.annotation.Id;

public class Order {

    @Id
    private String id;
    private boolean completed;
    private ShoppingCart cart;
    private PaymentDetails payment;
    private ShippingDetails shipping;
    private CustomerDetails customerDetails;

    public Order() {
    }

    public Order(String id, boolean completed, ShoppingCart cart, PaymentDetails payment, ShippingDetails shipping,
                 CustomerDetails customerDetails) {
        this.id = id;
        this.completed = completed;
        this.cart = cart;
        this.payment = payment;
        this.shipping = shipping;
        this.customerDetails = customerDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public PaymentDetails getPayment() {
        return payment;
    }

    public void setPayment(PaymentDetails payment) {
        this.payment = payment;
    }

    public ShippingDetails getShipping() {
        return shipping;
    }

    public void setShipping(ShippingDetails shipping) {
        this.shipping = shipping;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public void complete() {
        this.completed = true;
    }

}
