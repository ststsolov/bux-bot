package com.bux.quotes;

import com.bux.quotes.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@WebSocket
public class Socket implements QuoteSource {
    private static final Logger LOGGER = LogManager.getLogger(Socket.class);
    private static final ObjectMapper OBJECT_MAPPER = (new ObjectMapper()).registerModule(new JodaModule());

    private final String address;
    private final String auth;

    private final WebSocketClient client = new WebSocketClient();
    private CountDownLatch openConfirmedLatch = new CountDownLatch(1);

    private Session session;
    private final Map<String, List<QuoteSubscriber>> subscribers = new HashMap<>();

    public Socket(String address, String auth) {
        this.address = address;
        this.auth = auth;
    }

    public boolean addSubscriber(String productId, QuoteSubscriber s) {
        if (!isConnected()) {
            LOGGER.error("Trying to add subscriber, but socket isn't fully opened yet.");
            return false;
        }
        if (!subscribers.containsKey(productId)) {
            Subscription subscription = new Subscription(Collections.singletonList("trading.product." + productId), null);
            try {
                String message = OBJECT_MAPPER.writeValueAsString(subscription);
                LOGGER.trace("Sending " + message);
                session.getRemote().sendString(message);
            } catch (IOException e) {
                LOGGER.error("Couldn't send subscription request");
                return false;
            }
            subscribers.put(productId, new ArrayList<>());
        }
        subscribers.get(productId).add(s);
        LOGGER.info(s.toString() + " subscribed to quotes for " + productId);
        return true;
    }

    @Override
    public void start() throws Exception {
        LOGGER.info("Connecting to : " + address);
        client.start();
        URI uri = new URI(address);
        ClientUpgradeRequest request = new ClientUpgradeRequest();
        request.setHeader("Authorization", auth);
        request.setHeader("Accept-Language", "en;q=0.8");
        client.connect(this, uri, request);
        openConfirmedLatch.await(5, TimeUnit.SECONDS);
    }

    public void stop() throws Exception {
        if (session != null && session.isOpen()) {
            session.close();
        }
        if (client.isRunning()) {
            client.stop();
        }
    }

    @OnWebSocketMessage
    public void onMessage(String msg) {
        LOGGER.trace("Received message: " + msg);
        Event e = parseMessage(msg.getBytes(), Event.class);
        if (e == null) {
            // ignore unknown messages
            return;
        }
        switch (e.getType()) {
            case CONNECTED: {
                LOGGER.info("Received app connected event");
                openConfirmedLatch.countDown();
                return;
            }
            case CONNECT_FAILED: {
                LOGGER.error("Received failed to connect event");
                return;
            }
            case QUOTE: {
                QuoteEvent.Quote q = ((QuoteEvent) e).getBody();
                List<QuoteSubscriber> receivers = subscribers.get(q.getSecurityId());
                if (receivers != null) {
                    for (QuoteSubscriber s : receivers) {
                        try {
                            s.onQuote(q.getSecurityId(), new BigDecimal(q.getCurrentPrice()), q.getTimeStamp());
                        } catch (Exception ex) {
                            LOGGER.error("Error notifying subscriber for quote: " + q, ex);
                        }
                    }
                }
                return;
            }
        }
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        LOGGER.debug("Connection closed: " + statusCode + " - " + reason);
        this.session = null;
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        LOGGER.trace("Got connect: " + session);
        this.session = session;
    }

    public boolean isConnected() {
        return session != null && session.isOpen() && openConfirmedLatch.getCount() == 0;
    }

    public static <T> T parseMessage(byte[] message, Class<T> type) {
        try {
            return OBJECT_MAPPER.readValue(message, type);
        } catch (IOException e) {
            LOGGER.trace("Couldn't parse message: " + e.getMessage());
            return null;
        }
    }
}