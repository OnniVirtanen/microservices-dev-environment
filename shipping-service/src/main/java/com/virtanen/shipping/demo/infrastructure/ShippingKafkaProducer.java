package com.virtanen.shipping.demo.application;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShippingKafkaProducer implements EventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ShippingKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(ShipmentEvent event) {
        kafkaTemplate.send(event.getName(), event.getPayload());
    }

}
