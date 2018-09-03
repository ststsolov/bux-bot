package com.bux.quotes;

public interface QuoteSource {
    void start() throws Exception;
    void stop() throws Exception;
    boolean isConnected();
    boolean addSubscriber(String productId, QuoteSubscriber s);
}
