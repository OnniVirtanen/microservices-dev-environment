package com.virtanen.shipping.demo.application;

public interface EventConsumer {

    void consumePaymentProcessed(String message);

}
