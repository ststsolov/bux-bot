package com.bux.quotes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.joda.time.DateTime;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuoteEvent extends Event {

    private Quote body;

    public void setBody(Quote body) {
        this.body = body;
    }

    public Quote getBody() {
        return body;
    }

    public class Quote {
        private String securityId;
        private String currentPrice;
        private DateTime timeStamp;

        public void setSecurityId(String securityId) {
            this.securityId = securityId;
        }

        public void setCurrentPrice(String currentPrice) {
            this.currentPrice = currentPrice;
        }

        public void setTimeStamp(DateTime timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getSecurityId() {
            return this.securityId;
        }

        public String getCurrentPrice() {
            return this.currentPrice;
        }

        public DateTime getTimeStamp() {
            return this.timeStamp;
        }
    }

    @Override
    public EventType getType() {
        return EventType.QUOTE;
    }
}
