package com.bux.quotes;

import org.joda.time.DateTime;

import java.math.BigDecimal;

public interface QuoteSubscriber {
    void onQuote(String productId, BigDecimal currentPrice, DateTime timestamp);
}
