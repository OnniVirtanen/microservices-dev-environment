package com.virtanen.payment.demo.domain;

import com.virtanen.event.events.ReserveItemsEvent;
import org.springframework.transaction.annotation.Transactional;

public interface PaymentService {

    @Transactional(readOnly = false)
    void processPayment(ReserveItemsEvent event);

}
