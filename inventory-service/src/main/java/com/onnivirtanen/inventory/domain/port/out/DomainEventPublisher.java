package com.onnivirtanen.inventory.domain.port.out;

public interface DomainEventPublisher {
    void publish(DomainEvent<?> event);
}
