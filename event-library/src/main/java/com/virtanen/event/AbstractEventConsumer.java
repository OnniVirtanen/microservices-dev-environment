package com.virtanen.event;

public abstract class AbstractEventConsumer<T extends Event> {

    public abstract void consume(T event);

}
