package com.onnivirtanen.inventory.domain.event;

import com.virtanen.event.Event;

public class ProductAssignedNewShelfLocationEvent extends Event {

    private String productId;

    public ProductAssignedNewShelfLocationEvent() {
        super("productAssignedNewShelfLocation");
    }

    public ProductAssignedNewShelfLocationEvent(String productId) {
        this();
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

}
