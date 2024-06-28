package com.virtanen.order.domain.model;

import org.springframework.data.annotation.Id;

public class Order {

    @Id
    private String id;
    private ShoppingCart cart;
    private PaymentDetails payment;
    private ShippingDetails shipping;
    private CustomerDetails customerDetails;

    public Order() {
    }

    public Order(String id, ShoppingCart cart, PaymentDetails payment, ShippingDetails shipping,
                 CustomerDetails customerDetails) {
        this.id = id;
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

}
