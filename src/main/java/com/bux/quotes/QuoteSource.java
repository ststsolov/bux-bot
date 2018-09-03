package com.bux.quotes;

public interface QuoteSource {
    void start() throws Exception;
    void stop() throws Exception;
    boolean isConnected() throws Exception;
    boolean addSubscriber(String productId, QuoteSubscriber s);
}
