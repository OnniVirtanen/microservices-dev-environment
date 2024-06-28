package com.virtanen.payment.demo.domain;

import com.virtanen.payment.demo.domain.event.ReserveItemsEvent;
import org.springframework.transaction.annotation.Transactional;

public interface PaymentService {

    @Transactional(readOnly = false)
    void processPayment(ReserveItemsEvent event);

}
