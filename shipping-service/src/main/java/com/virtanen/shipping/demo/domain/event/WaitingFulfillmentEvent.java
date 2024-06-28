package com.virtanen.shipping.demo.domain.event;

import com.virtanen.event.Event;

public class WaitingFulfillmentEvent extends Event {

    private String orderId;
    private String shipmentId;

    public WaitingFulfillmentEvent() {
        super("waitingFulfillment");
    }

    public WaitingFulfillmentEvent(String orderId, String shipmentId) {
        this();
        this.orderId = orderId;
        this.shipmentId = shipmentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getShipmentId() {
        return shipmentId;
    }

}
