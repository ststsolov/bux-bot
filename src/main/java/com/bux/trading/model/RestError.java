//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bux.trading.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public final class RestError {
    private final String message;
    private final String developerMessage;
    private final String errorCode;

    public RestError(String message, String errorCode) {
        this(message, message, errorCode);
    }

    @JsonCreator
    public RestError(@JsonProperty("message") String message, @JsonProperty("developerMessage") String developerMessage, @JsonProperty("errorCode") String errorCode) {
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
