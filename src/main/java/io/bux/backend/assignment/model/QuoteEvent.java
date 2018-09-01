//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.bux.backend.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

public final class QuoteEvent {
    private static final String TYPE = "trading.quote";
    private final QuoteEventBody body;

    public QuoteEvent(QuoteEventBody body) {
        this.body = body;
    }

    public QuoteEvent(String securityId, String currentPrice, DateTime timeStamp) {
        this.body = new QuoteEventBody(securityId, currentPrice, timeStamp);
    }

    @JsonProperty("t")
    public String getType() {
        return "trading.quote";
    }

    public QuoteEventBody getBody() {
        return this.body;
    }
}
