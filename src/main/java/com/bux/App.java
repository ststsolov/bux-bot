package com.bux;

import com.bux.bot.BotCommand;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        if (args == null || args.length < 4) {
            logger.error("Please enter product id, entry price, limit price and stop price in this order.");
            System.exit(1);
        }
        String id = args[0];
        BigDecimal entry = new BigDecimal(args[1]);
        BigDecimal limit = new BigDecimal(args[2]);
        BigDecimal stop = new BigDecimal(args[3]);
        BotCommand command = new BotCommand(id, entry, limit, stop);
        if (command.isValid()) {
            logger.error("Bot command is invalid, check prices.");
            System.exit(1);
        }
    }
}
