package com.virtanen.payment.demo.application;

import com.virtanen.event.EventConsumer;
import com.virtanen.event.events.ReserveItemsEvent;
import com.virtanen.payment.demo.domain.PaymentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReserveItemsEventConsumer extends EventConsumer<ReserveItemsEvent> {

    private final PaymentService paymentService;

    public ReserveItemsEventConsumer(PaymentService paymentService) {
        super(ReserveItemsEvent.class);
        this.paymentService = paymentService;
    }

    @Override
    @KafkaListener(topics = "inventory", groupId = "items-reserved-group")
    public void listen(String message) {
        if (super.isRelevantEvent(message)) {
            consume(super.parseMessage(message));
        }
    }

    @Override
    protected void consume(ReserveItemsEvent event) {
        paymentService.processPayment(event);
    }

}
