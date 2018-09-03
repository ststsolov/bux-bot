package com.bux.trading;

import com.bux.quotes.QuoteSubscriber;
import com.bux.trading.model.BigMoney;
import com.bux.trading.model.TradeDirection;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import java.math.BigDecimal;

public class AutomatedTrader extends Trader implements QuoteSubscriber {
    private static final Logger LOGGER = LogManager.getLogger(AutomatedTrader.class);

    private final String productId;
    private final BigMoney amount;
    private final int leverage;
    private final BigDecimal entryPrice;
    private final BigDecimal limitPrice;
    private final BigDecimal stopPrice;

    private BigDecimal lastPrice;
    private String positionId;
    private boolean done = false;

    public AutomatedTrader(String address, String auth, String productId, BigMoney amount, int leverage,
                           BigDecimal entryPrice, BigDecimal limitPrice, BigDecimal stopPrice) {
        super(address, auth);
        this.productId = productId;
        this.amount = amount;
        this.leverage = leverage;
        this.entryPrice = entryPrice;
        this.limitPrice = limitPrice;
        this.stopPrice = stopPrice;
    }

    public String getProductId() {
        return productId;
    }

    public boolean isValid() {
        return stopPrice.compareTo(entryPrice) < 0 && entryPrice.compareTo(limitPrice) < 0;
    }

    public boolean isDone() {
        return done;
    }

    private boolean priceMatched(BigDecimal target, BigDecimal before, BigDecimal now) {
        if (target.equals(now)) {
            return true;
        }
        if (before != null) {
            if ((before.compareTo(target) < 0 && target.compareTo(now) < 0) ||
                    (before.compareTo(target) > 0 && target.compareTo(now) > 0)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized void onQuote(String productId, BigDecimal currentPrice, DateTime timestamp) {
        if (done || !this.productId.equals(productId)) {
            return;
        }
        if (positionId == null && priceMatched(entryPrice, lastPrice, currentPrice)) {
            try {
                positionId = openPosition(productId, amount, leverage, TradeDirection.BUY);
                LOGGER.info("Opened position with id " + positionId);
            } catch (Exception e) {
                LOGGER.error("Failed to open position, stopping bot...", e);
                done = true;
            }
        } else if (positionId != null && priceMatched(limitPrice, lastPrice, currentPrice)
                || positionId != null && priceMatched(stopPrice, lastPrice, currentPrice)) {
            try {
                closePosition(positionId);
                LOGGER.info("Closed position with id " + positionId);
            } catch (Exception e) {
                LOGGER.error("Failed to close position...", e);
            }
            done = true;
        }
        lastPrice = currentPrice;
    }
}
