package com.bux.quotes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "t")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConnectedEvent.class, name = "connect.connected"),
        @JsonSubTypes.Type(value = ConnectFailedEvent.class, name = "connect.failed"),
        @JsonSubTypes.Type(value = QuoteEvent.class, name = "trading.quote")
})
public abstract class Event {
    public abstract EventType getType();
}