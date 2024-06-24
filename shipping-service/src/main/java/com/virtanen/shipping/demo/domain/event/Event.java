package com.virtanen.shipping.demo.domain.model;

public abstract class Event {

    private final String id;
    private final String name;

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
