package com.bux.bot;

import java.math.BigDecimal;

public class BotCommand {

    private String productId;
    private BigDecimal entryPrice;
    private BigDecimal limitPrice;
    private BigDecimal stopPrice;

    public BotCommand(String productId, BigDecimal entryPrice, BigDecimal limitPrice, BigDecimal stopPrice) {
        this.productId = productId;
        this.entryPrice = entryPrice;
        this.limitPrice = limitPrice;
        this.stopPrice = stopPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(BigDecimal entryPrice) {
        this.entryPrice = entryPrice;
    }

    public BigDecimal getLimitPrice() {
        return limitPrice;
    }

    public void setLimitPrice(BigDecimal limitPrice) {
        this.limitPrice = limitPrice;
    }

    public BigDecimal getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(BigDecimal stopPrice) {
        this.stopPrice = stopPrice;
    }

    public boolean isValid() {
        return stopPrice.compareTo(entryPrice) < 0 && entryPrice.compareTo(limitPrice) < 0;
    }

    @Override
    public String toString() {
        return "BotCommand{" +
                "productId='" + productId + '\'' +
                ", entryPrice=" + entryPrice +
                ", limitPrice=" + limitPrice +
                ", stopPrice=" + stopPrice +
                '}';
    }
}
