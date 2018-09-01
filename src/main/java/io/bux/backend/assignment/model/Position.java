//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.bux.backend.assignment.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.joda.time.DateTime;

public final class Position {
    private final String id;
    private final DateTime dateCreated;
    private final PositionType type;
    private final Product product;
    private final BigMoney investingAmount;
    private final BigMoney tradePrice;
    private final BigDecimal tradeUnits;
    private final Integer leverage;
    private BigMoney profitAndLoss;
    private BigMoney currentPrice;

    public Position(String id, PositionType type, Product product, BigMoney investingAmount, BigMoney tradePrice, Integer leverage) {
        this.id = id;
        this.type = type;
        this.product = product;
        this.investingAmount = investingAmount;
        this.tradePrice = tradePrice;
        this.leverage = leverage;
        this.dateCreated = DateTime.now();
        this.currentPrice = tradePrice;
        this.tradeUnits = this.calculateTradeUnits(investingAmount, tradePrice, leverage);
        this.profitAndLoss = new BigMoney(investingAmount.getCurrency(), investingAmount.getDecimalPlaces(), new BigDecimal("0.00"));
    }

    public String getId() {
        return this.id;
    }

    public DateTime getDateCreated() {
        return this.dateCreated;
    }

    public PositionType getType() {
        return this.type;
    }

    public Product getProduct() {
        return this.product;
    }

    public BigMoney getInvestingAmount() {
        return this.investingAmount;
    }

    public BigMoney getTradePrice() {
        return this.tradePrice;
    }

    public BigMoney getProfitAndLoss() {
        return this.profitAndLoss;
    }

    public Integer getLeverage() {
        return this.leverage;
    }

    public BigMoney getCurrentPrice() {
        return this.currentPrice;
    }

    public void updateCurrentPrice(BigMoney currentPrice) {
        this.currentPrice = currentPrice;
        this.profitAndLoss = this.calculateProfitAndLoss(currentPrice);
    }

    private BigMoney calculateProfitAndLoss(BigMoney currentPrice) {
        BigDecimal profitAndLoss;
        if (this.type == PositionType.LONG) {
            profitAndLoss = currentPrice.getAmount().subtract(this.tradePrice.getAmount()).multiply(this.tradeUnits).setScale(2, RoundingMode.HALF_DOWN);
        } else {
            profitAndLoss = this.tradePrice.getAmount().subtract(currentPrice.getAmount()).multiply(this.tradeUnits).setScale(2, RoundingMode.HALF_DOWN);
        }

        return new BigMoney(this.investingAmount.getCurrency(), this.investingAmount.getDecimalPlaces(), profitAndLoss);
    }

    private BigDecimal calculateTradeUnits(BigMoney investedAmount, BigMoney tradePrice, int leverage) {
        BigDecimal baseMarginRate = BigDecimal.ONE.setScale(8, 3).divide(BigDecimal.valueOf((long)leverage), 8, 3);
        BigDecimal divisor = tradePrice.getAmount().multiply(baseMarginRate);
        return investedAmount.getAmount().setScale(8, 3).divide(divisor, 8, 3);
    }
}
