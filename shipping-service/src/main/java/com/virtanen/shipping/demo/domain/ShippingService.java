package com.virtanen.shipping.demo.domain;

import com.virtanen.shipping.demo.domain.model.InformShipmentEvent;

public interface ShippingService {

    void handleShipment(InformShipmentEvent event);

}
