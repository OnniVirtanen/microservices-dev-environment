package com.virtanen.order.domain;

import com.virtanen.order.domain.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
