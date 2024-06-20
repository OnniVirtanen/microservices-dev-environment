package com.virtanen.shipping.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShippingDetailsRepository extends MongoRepository<ShippingDetails, String> {

}
