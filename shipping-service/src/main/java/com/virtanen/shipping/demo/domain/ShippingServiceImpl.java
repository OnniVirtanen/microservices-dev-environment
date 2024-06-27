package com.virtanen.shipping.demo.domain;

import com.virtanen.shipping.demo.domain.event.PaymentProcessedEvent;
import com.virtanen.shipping.demo.domain.event.WaitingFulfillmentEvent;
import com.virtanen.shipping.demo.domain.model.ShippingDetails;
import org.springframework.stereotype.Service;

@Service
public class ShippingServiceImpl implements ShippingService {

    private final ShippingDetailsRepository repository;
    private final EventProducer producer;

    public ShippingServiceImpl(ShippingDetailsRepository repository, EventProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    public void handleShipment(PaymentProcessedEvent event) {
        ShippingDetails details = new ShippingDetails(null, event.getOrderId(), false);
        repository.save(details);
        producer.publish(new WaitingFulfillmentEvent(
                details.getOrderId(),
                details.getId()
        ));
    }

}
