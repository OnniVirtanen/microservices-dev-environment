package com.virtanen.order.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<String> productIds = new ArrayList<>();
    private BigDecimal totalPrice;

    public ShoppingCart(List<String> productIds, BigDecimal totalPrice) {
        this.productIds = productIds;
        this.totalPrice = totalPrice;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

}
