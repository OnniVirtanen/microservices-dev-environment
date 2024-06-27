package com.virtanen.shipping.demo.domain.event;

import com.virtanen.shipping.demo.domain.util.EventIdGenerator;


public abstract class Event {

    private String id;
    private String name;

    protected Event() {
    }

    protected Event(String name) {
        this.id = EventIdGenerator.generateId();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
