//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.bux.backend.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class FailureEvent {
    private final FailureEventBody body;
    private final String type;

    public FailureEvent(String type, String message, String errorCode) {
        this(type, new FailureEventBody(message, message, errorCode));
    }

    public FailureEvent(String type, FailureEventBody body) {
        this.type = type;
        this.body = body;
    }

    @JsonProperty("t")
    public String getType() {
        return this.type;
    }

    public FailureEventBody getBody() {
        return this.body;
    }
}
