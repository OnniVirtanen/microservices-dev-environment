package com.virtanen.shipping.demo.domain;

import com.virtanen.shipping.demo.domain.event.Event;

public interface EventProducer {

    void publish(Event event);

}
