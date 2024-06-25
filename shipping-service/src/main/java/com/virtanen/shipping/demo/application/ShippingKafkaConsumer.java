package com.virtanen.shipping.demo.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtanen.shipping.demo.domain.ShippingService;
import com.virtanen.shipping.demo.domain.event.PaymentProcessedEvent;
import com.virtanen.shipping.demo.domain.event.WaitingFulfillmentEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ShippingKafkaConsumer implements EventConsumer {

    private final ShippingService shippingService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(ShippingKafkaConsumer.class);

    public ShippingKafkaConsumer(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @Override
    @KafkaListener(topics = "payment", groupId = "shipment")
    public void consumePaymentProcessed(String message) {
        shippingService.handleShipment(parseMessage(message, PaymentProcessedEvent.class));
    }

    private <T> T parseMessage(String message, Class<T> clazz) {
        try {
            return objectMapper.readValue(message, clazz);
        } catch (JsonProcessingException e) {
            logger.log(Level.ERROR, "Error processing JSON message", e);
            return null;
        }
    }

}
