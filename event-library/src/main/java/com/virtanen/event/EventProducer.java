package com.virtanen.event;

public interface EventProducer {

    void publish(Event event, String topic);

}
