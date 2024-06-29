package com.virtanen.shipping.demo.domain.model;

import com.virtanen.shipping.demo.domain.event.dto.Address;
import org.springframework.data.annotation.Id;

public class Shipping {

    @Id
    private String id;
    private String orderId;
    private Address address;
    private boolean shipped;

    public Shipping() {
    }

    public Shipping(String id, String orderId, boolean shipped) {
        this.id = id;
        this.orderId = orderId;
        this.shipped = shipped;
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public boolean isShipped() {
        return shipped;
    }

    @Override
    public String toString() {
        return String.format(
                "Shipping[id=%s, orderId='%s', shipped='%s']",
                id, orderId, shipped);
    }

}
