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
public final class QuoteEventBody {
    private final String securityId;
    private final String currentPrice;
    private final DateTime timeStamp;

    public QuoteEventBody(String securityId, String currentPrice) {
        this(securityId, currentPrice, (DateTime)null);
    }

    @JsonCreator
    public QuoteEventBody(@JsonProperty("securityId") String securityId, @JsonProperty("currentPrice") String currentPrice, @JsonProperty("timeStamp") DateTime timeStamp) {
        this.securityId = securityId;
        this.currentPrice = currentPrice;
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
