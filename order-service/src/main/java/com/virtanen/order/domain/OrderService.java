package com.virtanen.order.domain;

import com.virtanen.order.application.dto.CreateOrderCommand;
import com.virtanen.order.domain.event.WaitingFulfillmentEvent;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {

    @Transactional(readOnly = false)
    void createOrder(CreateOrderCommand command);

    @Transactional(readOnly = false)
    void completeOrder(WaitingFulfillmentEvent event);

}
