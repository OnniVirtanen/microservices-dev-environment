package com.virtanen.shipping.demo.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtanen.shipping.demo.domain.ShippingService;
import com.virtanen.shipping.demo.domain.event.PaymentProcessedEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ShippingKafkaListener implements EventListener {

    private final ShippingService shippingService;
    private static final Logger logger = LogManager.getLogger(ShippingKafkaListener.class);

    public ShippingKafkaListener(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @Override
    @KafkaListener(topics = "payment", groupId = "shipment")
    public void listenPaymentProcessed(String message) {
        shippingService.handleShipment(parseMessage(message));
    }

    private PaymentProcessedEvent parseMessage(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(message, PaymentProcessedEvent.class);
        } catch (JsonProcessingException e) {
            logger.log(Level.ALL, e);
            return null;
        }
    }

}
