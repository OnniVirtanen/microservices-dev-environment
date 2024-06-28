package com.virtanen.shipping.demo.application.eventlisteners;

import com.virtanen.event.EventConsumer;
import com.virtanen.shipping.demo.domain.event.PaymentRefundedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentRefundedEventConsumer extends EventConsumer<PaymentRefundedEvent> {

    public PaymentRefundedEventConsumer() {
        super(PaymentRefundedEvent.class);
    }

    @Override
    @KafkaListener(topics = "payment", groupId = "payment-refunded-group")
    public void listen(String message) {
        if (super.isRelevantEvent(message)) {
            consume(super.parseMessage(message));
        }
    }

    @Override
    protected void consume(PaymentRefundedEvent event) {
        System.out.println("Payment was refunded");
    }

}
