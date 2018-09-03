//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bux.trading.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
public final class Trade {
    private final String id;
    private final String positionId;
    private final BigMoney profitAndLoss;
    private final Product product;
    private final BigMoney investingAmount;
    private final BigMoney price;
    private final Integer leverage;
    private final TradeDirection direction;
    private final TradeType type;
    private final DateTime dateCreated;

    @JsonCreator
    public Trade(@JsonProperty("id") String id, @JsonProperty("positionId") String positionId, @JsonProperty("profitAndLoss") BigMoney profitAndLoss, @JsonProperty("product") Product product, @JsonProperty("investingAmount") BigMoney investingAmount, @JsonProperty("price") BigMoney price, @JsonProperty("leverage") Integer leverage, @JsonProperty("direction") TradeDirection direction, @JsonProperty("type") TradeType type) {
        this.id = id;
        this.positionId = positionId;
        this.profitAndLoss = profitAndLoss;
        this.product = product;
        this.investingAmount = investingAmount;
        this.price = price;
        this.leverage = leverage;
        this.direction = direction;
        this.type = type;
        this.dateCreated = DateTime.now();
    }

    public String getId() {
        return this.id;
    }

    public String getPositionId() {
        return this.positionId;
    }

    public BigMoney getProfitAndLoss() {
        return this.profitAndLoss;
    }

    public Product getProduct() {
        return this.product;
    }

    public BigMoney getInvestingAmount() {
        return this.investingAmount;
    }

    public BigMoney getPrice() {
        return this.price;
    }

    public Integer getLeverage() {
        return this.leverage;
    }

    public TradeDirection getDirection() {
        return this.direction;
    }

    public TradeType getType() {
        return this.type;
    }

    public DateTime getDateCreated() {
        return this.dateCreated;
    }
}
