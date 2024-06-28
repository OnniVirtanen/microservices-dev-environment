package com.onnivirtanen.inventory.domain.event;

import com.virtanen.event.Event;

public class ProductRemovedFromSelectionEvent extends Event {

    private String productId;

    public ProductRemovedFromSelectionEvent() {
        super("productRemovedFromSelection");
    }

    public ProductRemovedFromSelectionEvent(String productId) {
        this();
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

}
