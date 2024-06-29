package com.onnivirtanen.inventory.application.eventconsumer;

import com.onnivirtanen.inventory.domain.port.in.InventoryService;
import com.virtanen.event.EventConsumer;
import com.virtanen.event.events.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedEventConsumer extends EventConsumer<OrderCreatedEvent> {

    private final InventoryService inventoryService;

    protected OrderCreatedEventConsumer(InventoryService inventoryService) {
        super(OrderCreatedEvent.class);
        this.inventoryService = inventoryService;
    }

    @Override
    @KafkaListener(topics = "order", groupId = "order-created-group-local")
    public void listen(String message) {
        if (super.isRelevantEvent(message)) {
            consume(super.parseMessage(message));
        }
    }

    @Override
    protected void consume(OrderCreatedEvent event) {
        inventoryService.reserveItems(event);
    }

}
