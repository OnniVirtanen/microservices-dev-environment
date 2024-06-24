package com.virtanen.shipping.demo.application;

import com.virtanen.shipping.demo.domain.ShippingDetailsRepository;
import com.virtanen.shipping.demo.domain.model.ShippingDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/shipping")
public class ShippingController {

    private final ShippingDetailsRepository repository;

    public ShippingController(ShippingDetailsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ShippingDetails> getShippingDetails() {
        return repository.findAll();
    }

}
