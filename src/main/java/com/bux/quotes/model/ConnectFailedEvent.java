package com.bux.quotes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConnectFailedEvent extends Event {
    @Override
    public EventType getType() {
        return EventType.CONNECT_FAILED;
    }
}
