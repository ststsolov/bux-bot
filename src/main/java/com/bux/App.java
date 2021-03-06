package com.bux;

import com.bux.quotes.QuoteSource;
import com.bux.quotes.Socket;
import com.bux.trading.Trader;
import com.bux.trading.model.BigMoney;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ResourceBundle;

public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);
    private static final ResourceBundle rb = ResourceBundle.getBundle("config");

    private static final Integer LEVERAGE = 1;
    private static final BigMoney AMOUNT = new BigMoney("BUX", 2, new BigDecimal("10.00"));

    public static void main(String[] args) throws Exception {
        if (args == null || args.length < 4) {
            LOGGER.error("Please enter product id, entry price, limit price and stop price in this order.");
            System.exit(1);
        }
        Bot bot = makeBot(args);
        if (!bot.isValid()) {
            LOGGER.error("Invalid parameters, check prices.");
            System.exit(1);
        }
        QuoteSource quoteSource = new Socket(rb.getString("socket"), rb.getString("auth"));

        try {
            quoteSource.start();
            if (!quoteSource.addSubscriber(bot.getProductId(), bot)) {
                LOGGER.error("Failed to subscribe for quotes, exiting.");
                quoteSource.stop();
                System.exit(1);
            }

            while (!bot.isDone() && quoteSource.isConnected()) {
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            quoteSource.stop();
        }
    }

    private static Bot makeBot(String[] args) {
        String id = args[0];
        BigDecimal entry = new BigDecimal(args[1]);
        BigDecimal limit = new BigDecimal(args[2]);
        BigDecimal stop = new BigDecimal(args[3]);
        Trader t = new Trader(rb.getString("rest"),
                rb.getString("auth"));
        return new Bot(t, id, AMOUNT, LEVERAGE, entry, limit, stop);
    }

    static {
        Unirest.setObjectMapper(new ObjectMapper() {
            private final com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper().registerModule(new JodaModule());

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
