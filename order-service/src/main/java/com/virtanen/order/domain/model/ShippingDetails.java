package com.virtanen.order.domain.model;

public class ShippingDetails {

    private String shippingType;
    private Address address;

    public ShippingDetails() {
    }

    public ShippingDetails(String shippingType, Address address) {
        this.shippingType = shippingType;
        this.address = address;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
