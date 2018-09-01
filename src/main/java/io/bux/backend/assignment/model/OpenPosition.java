//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.bux.backend.assignment.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public final class OpenPosition {
    private final String productId;
    private final BigMoney investingAmount;
    private final Integer leverage;
    private final TradeDirection direction;
    private final Source source;

    @JsonCreator
    public OpenPosition(@JsonProperty(value = "productId",required = true) String productId, @JsonProperty(value = "investingAmount",required = true) BigMoney investingAmount, @JsonProperty(value = "leverage",required = true) Integer leverage, @JsonProperty(value = "direction",required = true) TradeDirection direction, @JsonProperty("source") Source source) {
        this.productId = productId;
        this.investingAmount = investingAmount;
        this.leverage = leverage;
        this.direction = direction;
        this.source = source;
    }

    public String getProductId() {
        return this.productId;
    }

    public BigMoney getInvestingAmount() {
        return this.investingAmount;
    }

    public Integer getLeverage() {
        return this.leverage;
    }

    public TradeDirection getDirection() {
        return this.direction;
    }

    public Source getSource() {
        return this.source;
    }

    public Map<String, String> validate() {
        Map<String, String> validationErrors = new HashMap();
        if (!this.investingAmount.getCurrency().equals("BUX")) {
            validationErrors.put("investingAmount", "Investing currency must be 'BUX'");
        }

        if (this.investingAmount.getAmount().signum() != 1) {
            validationErrors.put("investingAmount", "Investing amount must be positive");
        }

        if (this.investingAmount.getDecimalPlaces() < 0) {
            validationErrors.put("investingAmount", "Investing decimals must be positive");
        }

        if (this.leverage <= 0) {
            validationErrors.put("leverage", "Leverage must be 1 or higher");
        }

        return validationErrors;
    }
}
