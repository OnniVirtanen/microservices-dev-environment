package com.onnivirtanen.inventory.domain.event;

import com.virtanen.event.Event;

public class ProductMarkedMissingEvent extends Event {

    private String productId;

    public ProductMarkedMissingEvent() {
        super("productMarkedMissing");
    }

    public ProductMarkedMissingEvent(String productId) {
        this();
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

}
