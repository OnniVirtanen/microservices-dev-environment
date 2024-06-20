package com.virtanen.shipping.demo.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtanen.shipping.demo.domain.ShippingService;
import com.virtanen.shipping.demo.domain.model.InformShipmentEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ShippingKafkaListener {

    private final ShippingService shippingService;

    public ShippingKafkaListener(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @KafkaListener(topics = "informShipment", groupId = "shipment-service-group")
    public void listenInformShipment(String message) {
        shippingService.handleShipment(parseMessage(message));
    }

    private InformShipmentEvent parseMessage(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(message, InformShipmentEvent.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
