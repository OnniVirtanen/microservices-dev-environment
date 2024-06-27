package com.virtanen.shipping.demo.application.eventlisteners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtanen.shipping.demo.domain.event.Event;

public abstract class EventListener<T extends Event> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> type;

    protected EventListener(Class<T> type) {
        this.type = type;
    }

    protected T parseMessage(String message) {
        try {
            return objectMapper.readValue(message, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse message", e);
        }
    }

    protected boolean isRelevantEvent(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode nameNode = jsonNode.get("name");
            if (nameNode != null) {
                return nameNode.asText().equals(getEventName());
            }
            return false;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse message", e);
        }
    }

    private String getEventName() {
        try {
            Event instance = type.getDeclaredConstructor().newInstance();
            return instance.getName();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get event name from type", e);
        }
    }

    public abstract void listen(String message);

    abstract void consume(T event);

}