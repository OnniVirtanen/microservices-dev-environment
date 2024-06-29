package com.virtanen.order.domain.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private Map<String, Integer> productsWithAmounts = new HashMap<>();
    private BigDecimal totalPrice;

    public ShoppingCart() {
    }

    public ShoppingCart(Map<String, Integer> productsWithAmounts, BigDecimal totalPrice) {
        this.productsWithAmounts = productsWithAmounts;
        this.totalPrice = totalPrice;
    }

    public Map<String, Integer> getProductsWithAmounts() {
        return productsWithAmounts;
    }

    public void setProductsWithAmounts(Map<String, Integer> productsWithAmounts) {
        this.productsWithAmounts = productsWithAmounts;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}
