package com.virtanen.shipping.demo.domain.model;

public class InformShipmentEvent {

    private String orderId;
    private boolean paid;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

}
