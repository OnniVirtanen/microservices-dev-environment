package com.virtanen.order.domain;

import com.virtanen.event.EventProducer;
import com.virtanen.order.application.dto.CreateOrderCommand;
import com.virtanen.order.domain.event.OrderCreatedEvent;
import com.virtanen.order.domain.model.Order;
import org.springframework.stereotype.Service;

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
        Order order = new Order(null, command.getCart(), command.getPayment(), command.getShipping(), command.getCustomerDetails());
        Order savedOrder = orderRepository.save(order);
        eventProducer.publish(new OrderCreatedEvent(savedOrder.getId(), savedOrder.getCart(), savedOrder.getPayment(),
                savedOrder.getShipping(), savedOrder.getCustomerDetails()), "order");
    }

}
