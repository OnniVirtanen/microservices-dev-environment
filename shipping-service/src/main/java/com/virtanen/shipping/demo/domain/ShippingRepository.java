package com.virtanen.shipping.demo.domain;

import com.virtanen.shipping.demo.domain.model.Shipping;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShippingRepository extends MongoRepository<Shipping, String> {

}
