package com.virtanen.payment.demo.domain.event;

import com.virtanen.event.Event;

public class ReserveItemsEvent extends Event {

    private String itemsReservedId;
    private String orderId;

    public ReserveItemsEvent() {
        super("itemsReserved");
    }

    public ReserveItemsEvent(String itemsReservedId, String orderId) {
        this();
        this.itemsReservedId = itemsReservedId;
        this.orderId = orderId;
    }

    public String getItemsReservedId() {
        return itemsReservedId;
    }

    public String getOrderId() {
        return orderId;
    }

}
