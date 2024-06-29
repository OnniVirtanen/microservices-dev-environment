package com.virtanen.shipping.demo.application;

import com.virtanen.shipping.demo.domain.ShippingRepository;
import com.virtanen.shipping.demo.domain.model.Shipping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/shipping")
public class ShippingController {

    private final ShippingRepository repository;

    public ShippingController(ShippingRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Shipping> getShippingDetails() {
        return repository.findAll();
    }

}
