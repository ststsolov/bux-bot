package com.bux;

import com.bux.trading.Trader;
import com.bux.trading.model.BigMoney;
import com.bux.trading.model.TradeDirection;
import junit.framework.TestCase;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class BotTest extends TestCase {

    private static final Integer LEVERAGE = 1;
    private static final BigMoney AMOUNT = new BigMoney("BUX", 2, new BigDecimal("10.00"));

    public BotTest(String testName) {
        super(testName);
    }

    public void testRisingExactEntryExactLimit() throws Exception {
        Trader trader = mock(Trader.class);
        String uuid = UUID.randomUUID().toString();
        when(trader.openPosition(any(), any(), any(), any())).thenReturn(uuid);
        String product = "sb26502";
        Bot bot = new Bot(trader, product, AMOUNT, LEVERAGE,
                new BigDecimal(10), new BigDecimal(11), new BigDecimal(12));

        bot.onQuote(product, new BigDecimal(9), new DateTime());
        bot.onQuote(product, new BigDecimal(9.3), new DateTime());
        bot.onQuote(product, new BigDecimal(9.9), new DateTime());
        verify(trader, never()).openPosition(any(), any(), any(), any());
        bot.onQuote(product, new BigDecimal(10), new DateTime());
        verify(trader).openPosition(eq(product), eq(AMOUNT), eq(LEVERAGE), eq(TradeDirection.BUY));
        bot.onQuote(product, new BigDecimal(10.5), new DateTime());
        bot.onQuote(product, new BigDecimal(10.9), new DateTime());
        verify(trader, never()).closePosition(any());
        bot.onQuote(product, new BigDecimal(11), new DateTime());
        verify(trader).closePosition(eq(uuid));
    }

    public void testFallingSkipEntrySkipStop() throws Exception {
        Trader trader = mock(Trader.class);
        String uuid = UUID.randomUUID().toString();
        when(trader.openPosition(any(), any(), any(), any())).thenReturn(uuid);
        String product = "sb26502";
        Bot bot = new Bot(trader, product, AMOUNT, LEVERAGE,
                new BigDecimal(10), new BigDecimal(11), new BigDecimal(9));

        bot.onQuote(product, new BigDecimal(11.1), new DateTime());
        bot.onQuote(product, new BigDecimal(10.8), new DateTime());
        bot.onQuote(product, new BigDecimal(10.2), new DateTime());
        verify(trader, never()).openPosition(any(), any(), any(), any());
        bot.onQuote(product, new BigDecimal(9.97), new DateTime());
        verify(trader).openPosition(eq(product), eq(AMOUNT), eq(LEVERAGE), eq(TradeDirection.BUY));
        bot.onQuote(product, new BigDecimal(9.4), new DateTime());
        bot.onQuote(product, new BigDecimal(9.2), new DateTime());
        verify(trader, never()).closePosition(any());
        bot.onQuote(product, new BigDecimal(8.99), new DateTime());
        verify(trader).closePosition(eq(uuid));
    }
}
