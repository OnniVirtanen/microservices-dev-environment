package com.virtanen.shipping.demo.application.eventlisteners;

import com.virtanen.event.EventConsumer;
import com.virtanen.event.events.PaymentProcessedEvent;
import com.virtanen.shipping.demo.domain.ShippingService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessedEventConsumer extends EventConsumer<PaymentProcessedEvent> {

    private final ShippingService shippingService;

    public PaymentProcessedEventConsumer(ShippingService shippingService) {
        super(PaymentProcessedEvent.class);
        this.shippingService = shippingService;
    }

    @Override
    @KafkaListener(topics = "payment", groupId = "payment-processed-group")
    public void listen(String message) {
        if (super.isRelevantEvent(message)) {
            consume(super.parseMessage(message));
        }
    }

    @Override
    protected void consume(PaymentProcessedEvent event) {
        shippingService.handleShipment(event);
    }

}
