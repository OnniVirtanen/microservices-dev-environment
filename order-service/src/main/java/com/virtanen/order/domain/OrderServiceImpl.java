package com.virtanen.order.domain;

import com.virtanen.event.EventProducer;
import com.virtanen.order.application.dto.CreateOrderCommand;
import com.virtanen.order.domain.event.OrderCompletedEvent;
import com.virtanen.order.domain.event.OrderCreatedEvent;
import com.virtanen.order.domain.event.WaitingFulfillmentEvent;
import com.virtanen.order.domain.model.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final EventProducer eventProducer;

    public OrderServiceImpl(OrderRepository orderRepository, EventProducer eventProducer) {
        this.orderRepository = orderRepository;
        this.eventProducer = eventProducer;
    }

    @Override
    public void createOrder(CreateOrderCommand command) {
        Order order = orderRepository.save(new Order(null, false, command.getCart(), command.getPayment(), command.getShipping(), command.getCustomerDetails()));
        eventProducer.publish(new OrderCreatedEvent(order.getId(), order.getCart(), order.getPayment(),
                order.getShipping(), order.getCustomerDetails()), "order");
    }

    @Override
    public void completeOrder(WaitingFulfillmentEvent event) {
        Optional<Order> order = orderRepository.findById(event.getOrderId());
        if (order.isEmpty()) {
            return;
        }
        Order o = order.get();
        o.complete();
        orderRepository.save(o);
        eventProducer.publish(new OrderCompletedEvent(o.getId()), "order");
    }

}
