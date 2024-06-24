package com.virtanen.shipping.demo.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtanen.shipping.demo.domain.EventProducer;
import com.virtanen.shipping.demo.domain.event.Event;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShippingKafkaProducer implements EventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LogManager.getLogger(ShippingKafkaProducer.class);
    private static final String TOPIC = "shipping";

    public ShippingKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void publish(Event event) {
        try {
            String eventJson = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, eventJson);
            logger.log(Level.DEBUG, "sent event" + eventJson);
        } catch (JsonProcessingException e) {;
            logger.log(Level.ERROR, e);
        }
    }

}
