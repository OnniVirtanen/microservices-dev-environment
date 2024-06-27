package com.virtanen.shipping.demo.application.eventlisteners;

import com.virtanen.shipping.demo.domain.ShippingService;
import com.virtanen.shipping.demo.domain.event.PaymentProcessedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessedEventListener extends EventListener<PaymentProcessedEvent> {

    private final ShippingService shippingService;

    public PaymentProcessedEventListener(ShippingService shippingService) {
        super(PaymentProcessedEvent.class);
        this.shippingService = shippingService;
    }

    @Override
    @KafkaListener(topics = "payment", groupId = "payment-processed-group")
    public void listen(String message) {
        if (isRelevantEvent(message)) {
            consume(this.parseMessage(message));
        }
    }

    @Override
    void consume(PaymentProcessedEvent event) {
        shippingService.handleShipment(event);
    }

}
