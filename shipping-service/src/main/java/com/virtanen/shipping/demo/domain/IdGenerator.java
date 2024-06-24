package com.virtanen.shipping.demo.domain;

import java.util.UUID;

public interface IdGenerator {

    static String generateEventId() {
        return UUID.randomUUID().toString();
    }

}
