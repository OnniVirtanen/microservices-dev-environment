package com.virtanen.order.domain.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private Map<String, Integer> productsWithAmounts = new HashMap<>();
    private BigDecimal totalPrice;

    public Map<String, Integer> getProductsWithAmounts() {
        return productsWithAmounts;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

}
