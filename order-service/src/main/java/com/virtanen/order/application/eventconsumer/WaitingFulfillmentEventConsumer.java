package com.virtanen.order.application.eventconsumer;

import com.virtanen.event.EventConsumer;
import com.virtanen.event.events.WaitingFulfillmentEvent;
import com.virtanen.order.domain.OrderService;
import org.springframework.kafka.annotation.KafkaListener;

public class WaitingFulfillmentEventConsumer extends EventConsumer<WaitingFulfillmentEvent> {

    private final OrderService orderService;

    public WaitingFulfillmentEventConsumer(OrderService orderService) {
        super(WaitingFulfillmentEvent.class);
        this.orderService = orderService;
    }

    @Override
    @KafkaListener(topics = "shipping", groupId = "shipping-waiting-fulfillment-group")
    public void listen(String message) {
        if (super.isRelevantEvent(message)) {
            consume(super.parseMessage(message));
        }
    }

    @Override
    protected void consume(WaitingFulfillmentEvent event) {
        orderService.completeOrder(event);
    }

}
