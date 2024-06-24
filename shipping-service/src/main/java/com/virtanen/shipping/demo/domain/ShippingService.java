package com.virtanen.shipping.demo.domain;

import com.virtanen.shipping.demo.domain.event.PaymentProcessedEvent;
import org.springframework.transaction.annotation.Transactional;

public interface ShippingService {

    @Transactional(readOnly = false)
    void handleShipment(PaymentProcessedEvent event);

}
