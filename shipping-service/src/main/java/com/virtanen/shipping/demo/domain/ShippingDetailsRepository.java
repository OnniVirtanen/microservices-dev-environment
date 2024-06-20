package com.virtanen.shipping.demo.domain;

import com.virtanen.shipping.demo.domain.model.ShippingDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShippingDetailsRepository extends MongoRepository<ShippingDetails, String> {

}
