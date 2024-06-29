package com.virtanen.payment.demo.domain.event.dto;

public class ShippingDetails {

    private String shippingType;
    private Address address;

    public ShippingDetails(String shippingType, Address address) {
        this.shippingType = shippingType;
        this.address = address;
    }

    public String getShippingType() {
        return shippingType;
    }

    public Address getAddress() {
        return address;
    }

}
