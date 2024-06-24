package com.virtanen.shipping.demo.domain.event;

import com.virtanen.shipping.demo.domain.util.EventIdGenerator;

public class WaitingFulfillmentEvent extends Event {

    private String orderId;
    private String shipmentId;

    public WaitingFulfillmentEvent() {
    }

    public WaitingFulfillmentEvent(String orderId, String shipmentId) {
        super(EventIdGenerator.generateId(), "_waitingFulfillment");
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
