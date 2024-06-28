package com.virtanen.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer implements EventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LogManager.getLogger(KafkaProducer.class);

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void publish(Event event, String topic) {
        try {
            String eventJson = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(topic, eventJson);
            logger.debug("sent event: {}", eventJson);
        } catch (JsonProcessingException e) {;
            logger.error(e);
        }
    }

}