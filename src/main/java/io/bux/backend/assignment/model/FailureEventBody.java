//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.bux.backend.assignment.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class FailureEventBody {
    private final String message;
    private final String developerMessage;
    private final String errorCode;

    @JsonCreator
    public FailureEventBody(@JsonProperty("message") String message, @JsonProperty("developerMessage") String developerMessage, @JsonProperty("errorCode") String errorCode) {
        this.message = message;
        this.developerMessage = developerMessage;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDeveloperMessage() {
        return this.developerMessage;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
