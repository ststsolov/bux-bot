//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.bux.backend.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

public class ConnectedEvent {
    private static final String TYPE = "connect.connected";
    private final ConnectedEventBody body;

    public ConnectedEvent(ConnectedEventBody body) {
        this.body = body;
    }

    public ConnectedEvent(String userId, String sessionId, DateTime time) {
        this.body = new ConnectedEventBody(userId, sessionId, time);
    }

    @JsonProperty("t")
    public String getType() {
        return "connect.connected";
    }

    public ConnectedEventBody getBody() {
        return this.body;
    }
}
