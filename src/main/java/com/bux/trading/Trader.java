package com.bux.trading;

import com.bux.trading.model.BigMoney;
import com.bux.trading.model.OpenPosition;
import com.bux.trading.model.Trade;
import com.bux.trading.model.TradeDirection;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;

public class Trader {
    private static final Logger LOGGER = LogManager.getLogger(Trader.class);

    private final String auth;
    private final String openPositionUrl;
    private final String deletePositionUrl;

    public Trader(String address, String auth) {
        this.auth = auth;
        this.openPositionUrl = address + "/trades";
        this.deletePositionUrl = address + "/portfolio/positions/{positionId}";
    }

    public String openPosition(String productId, BigMoney amount, Integer leverage, TradeDirection direction) throws Exception {
        OpenPosition op = new OpenPosition(productId, amount, leverage, direction, null);
        HttpResponse<Trade> response = Unirest.post(openPositionUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", auth)
                .body(op)
                .asObject(Trade.class);
        if (HttpStatus.OK_200 != response.getStatus()) {
            throw new RuntimeException("Failed to open a position: "
                    + response.getStatus() + " - " + response.getStatusText());
        }
        String positionId = response.getBody().getPositionId();
        LOGGER.debug("Opened position with id " + positionId);
        return positionId;
    }

    public void closePosition(String positionId) throws UnirestException {
        HttpResponse<String> response = Unirest.delete(deletePositionUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", auth)
                .routeParam("positionId", positionId)
                .asString();
        if (HttpStatus.OK_200 != response.getStatus()) {
            throw new RuntimeException("Failed to close a position: "
                    + response.getStatus() + " - " + response.getStatusText());
        }
        LOGGER.debug("Closed position with id " + positionId);
    }
}
