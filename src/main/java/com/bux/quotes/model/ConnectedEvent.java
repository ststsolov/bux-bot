package com.bux.quotes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.joda.time.DateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConnectedEvent extends Event {

    private Connected body;

    public Connected getBody() {
        return body;
    }

    public void setBody(Connected body) {
        this.body = body;
    }

    @JsonIgnoreProperties(
            ignoreUnknown = true
    )
    public class Connected {
        private String userId;
        private String sessionId;
        private DateTime time;

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public void setTime(DateTime time) {
            this.time = time;
        }

        public String getUserId() {
            return userId;
        }

        public String getSessionId() {
            return sessionId;
        }

        public DateTime getTime() {
            return time;
        }
    }

    @Override
    public EventType getType() {
        return EventType.CONNECTED;
    }
}
