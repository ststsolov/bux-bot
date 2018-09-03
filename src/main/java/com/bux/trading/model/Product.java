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

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
public final class Product {
    private final String securityId;
    private final String symbol;
    private final String displayName;
    private final BigMoney closingPrice;
    private BigMoney currentPrice;

    @JsonCreator
    public Product(@JsonProperty("securityId") String securityId, @JsonProperty("symbol") String symbol, @JsonProperty("displayName") String displayName, @JsonProperty("currentPrice") BigMoney currentPrice, @JsonProperty("closingPrice") BigMoney closingPrice) {
        this.securityId = securityId;
        this.symbol = symbol;
        this.displayName = displayName;
        this.currentPrice = currentPrice;
        this.closingPrice = closingPrice;
    }

    public String getSecurityId() {
        return this.securityId;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public BigMoney getCurrentPrice() {
        return this.currentPrice;
    }

    public void setCurrentPrice(BigMoney currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigMoney getClosingPrice() {
        return this.closingPrice;
    }
}
