package com.virtanen.payment.demo.domain;

import com.virtanen.event.EventProducer;
import com.virtanen.payment.demo.domain.event.PaymentProcessedEvent;
import com.virtanen.payment.demo.domain.event.ReserveItemsEvent;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final EventProducer eventProducer;

    public PaymentServiceImpl(EventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    @Override
    public void processPayment(ReserveItemsEvent event) {
        System.out.println("Payment processed with payment details: " + event.getPayment());
        // process payment mock
        String paymentId = UUID.randomUUID().toString();

        eventProducer.publish(new PaymentProcessedEvent(paymentId, event.getOrderId(), event.getShipping(),
                event.getCustomerDetails()), "payment");
    }

}
