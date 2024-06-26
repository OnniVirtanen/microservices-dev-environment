package com.virtanen.event;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class EventConsumer<T extends Event> {

    private static final Logger logger = LogManager.getLogger(EventConsumer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> type;

    protected EventConsumer(Class<T> type) {
        this.type = type;
    }

    protected T parseMessage(String message) {
        try {
            logger.debug("parsing message: {}", message);
            return objectMapper.readValue(message, type);
        } catch (JsonProcessingException e) {
            logger.error("Failed to parse message", e);
            throw new RuntimeException("Failed to parse message", e);
        }
    }

    protected boolean isRelevantEvent(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode nameNode = jsonNode.get("name");
            if (nameNode != null) {
                boolean isRelevant = nameNode.asText().equals(getEventName());
                logger.debug("event is relevant: {}", isRelevant);
                return isRelevant;
            }
            return false;
        } catch (JsonProcessingException e) {
            logger.error("Failed to parse message", e);
            throw new RuntimeException("Failed to parse message", e);
        }
    }

    private String getEventName() {
        try {
            Event instance = type.getDeclaredConstructor().newInstance();
            logger.debug("name: {}", instance.getName());
            return instance.getName();
        } catch (Exception e) {
            logger.error("Failed to get event name from type", e);
            throw new RuntimeException("Failed to get event name from type", e);
        }
    }

    public abstract void listen(String message);

    protected abstract void consume(T event);

}
