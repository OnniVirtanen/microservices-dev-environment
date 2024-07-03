package com.virtanen.event.example;

import com.virtanen.event.AbstractEventConsumer;
import com.virtanen.event.EventConsumer;
import com.virtanen.event.events.WaitingFulfillmentEvent;
import org.springframework.stereotype.Service;

@Service
@EventConsumer(eventType = WaitingFulfillmentEvent.class, topic = "shipping")
public class WaitingFulfillmentEventConsumer extends AbstractEventConsumer<WaitingFulfillmentEvent> {

    @Override
    public void consume(WaitingFulfillmentEvent event) {
        System.out.println(event);
    }

}