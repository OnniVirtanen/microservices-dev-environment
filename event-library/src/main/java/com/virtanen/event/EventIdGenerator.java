package com.virtanen.event;

import java.util.UUID;

public class EventIdGenerator {

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

}