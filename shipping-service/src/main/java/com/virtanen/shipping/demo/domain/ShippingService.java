package com.virtanen.shipping.demo.domain;

import com.virtanen.event.events.PaymentProcessedEvent;
import org.springframework.transaction.annotation.Transactional;

public interface ShippingService {

    @Transactional(readOnly = false)
    void handleShipment(PaymentProcessedEvent event);

}
