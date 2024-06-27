package com.virtanen.shipping.demo.application.eventlisteners;

import com.virtanen.shipping.demo.domain.event.PaymentRefundedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentRefundedEventListener extends EventListener<PaymentRefundedEvent> {

    public PaymentRefundedEventListener() {
        super(PaymentRefundedEvent.class);
    }

    @Override
    @KafkaListener(topics = "payment", groupId = "payment-refunded-group")
    public void listen(String message) {
        if (this.isRelevantEvent(message)) {
            consume(this.parseMessage(message));
        }
    }

    @Override
    void consume(PaymentRefundedEvent event) {
        System.out.println("Payment was refunded");
    }

}
