package com.onnivirtanen.inventory.domain.event.dto;

import java.math.BigDecimal;

public class PaymentDetails {

    private String paymentMethod;
    private CardDetails cardDetails;
    private String currency;
    private BigDecimal transactionAmount;

    public PaymentDetails(String paymentMethod, CardDetails cardDetails, String currency, BigDecimal transactionAmount) {
        this.paymentMethod = paymentMethod;
        this.cardDetails = cardDetails;
        this.currency = currency;
        this.transactionAmount = transactionAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public CardDetails getCardDetails() {
        return cardDetails;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

}
