package com.virtanen.shipping.demo.domain;

import com.virtanen.event.EventProducer;
import com.virtanen.shipping.demo.domain.event.PaymentProcessedEvent;
import com.virtanen.shipping.demo.domain.event.WaitingFulfillmentEvent;
import com.virtanen.shipping.demo.domain.model.Shipping;
import org.springframework.stereotype.Service;

@Service
public class ShippingServiceImpl implements ShippingService {

    private final ShippingRepository repository;
    private final EventProducer producer;

    public ShippingServiceImpl(ShippingRepository repository, EventProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    public void handleShipment(PaymentProcessedEvent event) {
        Shipping shipping = new Shipping(null, event.getOrderId(), false);
        repository.save(shipping);
        producer.publish(new WaitingFulfillmentEvent(
                shipping.getOrderId(),
                shipping.getId()
        ), "shipment");
    }

}
