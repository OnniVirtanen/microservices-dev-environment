package com.virtanen.shipping.demo.domain.event;

public abstract class Event {

    private String id;
    private String name;

    public Event() {
    }

    protected Event(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
