package com.onnivirtanen.inventory.domain.event;

import com.virtanen.event.Event;

import java.util.HashMap;
import java.util.Map;

public class OrderCreatedEvent extends Event {

    private String orderId;
    private Map<String, Integer> productsAmount = new HashMap<>();

    public OrderCreatedEvent() {
        super("orderCreated");
    }

    public OrderCreatedEvent(String orderId, Map<String, Integer> productsAmount) {
        this();
        this.orderId = orderId;
        this.productsAmount = productsAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public Map<String, Integer> getProductsAmount() {
        return productsAmount;
    }

}
