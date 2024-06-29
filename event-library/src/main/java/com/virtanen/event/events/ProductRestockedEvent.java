package com.virtanen.event.events;

import com.virtanen.event.Event;

public class ProductRestockedEvent extends Event {

    private String productId;

    public ProductRestockedEvent() {
        super("productRestocked");
    }

    public ProductRestockedEvent(String productId) {
        this();
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

}