package com.virtanen.shipping.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// add path
public class ShippingController {

    private final ShippingDetailsRepository repository;

    public ShippingController(ShippingDetailsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ShippingDetails> getShippingDetails() {
        repository.findAll();
    }

}
