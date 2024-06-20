package com.virtanen.shipping.demo;

import org.springframework.data.annotation.Id;

public class ShippingDetails {

    @Id
    private String id;
    private String orderId;
    private boolean shipped;

    public ShippingDetails() {
    }

    public ShippingDetails(String id, String orderId, boolean shipped) {
        this.id = id;
        this.orderId = orderId;
        this.shipped = shipped;
    }


    @Override
    public String toString() {
        return String.format(
                "Shipping[id=%s, orderId='%s', shipped='%s']",
                id, orderId, shipped);
    }

}
