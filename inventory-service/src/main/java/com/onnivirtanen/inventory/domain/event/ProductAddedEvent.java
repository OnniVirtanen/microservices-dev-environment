package com.onnivirtanen.inventory.domain.event;

import com.virtanen.event.Event;

public class ProductAddedEvent extends Event {

    private String productId;

    public ProductAddedEvent() {
        super("productAdded");
    }

    public ProductAddedEvent(String productId) {
        this();
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

}
