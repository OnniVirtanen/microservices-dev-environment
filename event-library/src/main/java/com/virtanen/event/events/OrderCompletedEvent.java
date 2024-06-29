package com.virtanen.event.events;


import com.virtanen.event.Event;

public class OrderCompletedEvent extends Event {

    private String orderId;

    public OrderCompletedEvent() {
        super("orderCompleted");
    }

    public OrderCompletedEvent(String orderId) {
        this();
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

}