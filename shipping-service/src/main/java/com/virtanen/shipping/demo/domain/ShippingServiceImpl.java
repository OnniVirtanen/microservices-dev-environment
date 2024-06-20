package com.virtanen.shipping.demo.domain;

import com.virtanen.shipping.demo.domain.model.InformShipmentEvent;
import com.virtanen.shipping.demo.domain.model.ShippingDetails;
import org.springframework.stereotype.Service;

@Service
public class ShippingServiceImpl implements ShippingService {

    private final ShippingDetailsRepository repository;

    public ShippingServiceImpl(ShippingDetailsRepository repository) {
        this.repository = repository;
    }

    public void handleShipment(InformShipmentEvent event) {
        if (event != null) {
            ShippingDetails details = new ShippingDetails(null, event.getOrderId(), false);
            repository.save(details);
        }
    }

}
