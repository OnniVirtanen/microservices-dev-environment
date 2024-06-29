package com.virtanen.event.events.dto;

import java.math.BigDecimal;

public class PaymentDetails {

    private String paymentMethod;
    private CardDetails cardDetails;
    private String currency;
    private BigDecimal transactionAmount;

    public PaymentDetails() {
    }

    public PaymentDetails(String paymentMethod, CardDetails cardDetails, String currency, BigDecimal transactionAmount) {
        this.paymentMethod = paymentMethod;
        this.cardDetails = cardDetails;
        this.currency = currency;
        this.transactionAmount = transactionAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public CardDetails getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(CardDetails cardDetails) {
        this.cardDetails = cardDetails;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

}
