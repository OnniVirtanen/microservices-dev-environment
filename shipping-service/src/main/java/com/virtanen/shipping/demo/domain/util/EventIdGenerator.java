package com.virtanen.shipping.demo.domain.event;

import java.util.UUID;

public class EventIdGenerator {

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

}
