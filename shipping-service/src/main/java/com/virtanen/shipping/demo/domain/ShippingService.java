package com.virtanen.shipping.demo.domain;

import com.virtanen.shipping.demo.domain.model.InformShipmentEvent;
import org.springframework.transaction.annotation.Transactional;

public interface ShippingService {

    @Transactional(readOnly = false)
    void handleShipment(InformShipmentEvent event);

}
