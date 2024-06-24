package com.virtanen.shipping.demo.domain;

import com.virtanen.shipping.demo.domain.model.Event;

public interface EventPublisher {

    void publish(Event event);

}
