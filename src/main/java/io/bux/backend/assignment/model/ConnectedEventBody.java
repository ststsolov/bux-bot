//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.bux.backend.assignment.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.joda.time.DateTime;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
public final class ConnectedEventBody {
    private final String userId;
    private final String sessionId;
    private final DateTime time;

    @JsonCreator
    public ConnectedEventBody(@JsonProperty("userId") String userId, @JsonProperty("sessionId") String sessionId, @JsonProperty("time") DateTime time) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.time = time;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public DateTime getTime() {
        return this.time;
    }
}
