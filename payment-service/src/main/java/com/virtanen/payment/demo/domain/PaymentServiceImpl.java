package com.virtanen.payment.demo.domain;

import com.virtanen.payment.demo.domain.event.ReserveItemsEvent;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public void processPayment(ReserveItemsEvent event) {
        System.out.println("Payment processed with event: " + event);
    }

}
