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
import java.util.List;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
public final class Subscription {
    private final List<String> subscribeTo;
    private final List<String> unsubscribeFrom;

    @JsonCreator
    public Subscription(@JsonProperty("subscribeTo") List<String> subscribeTo, @JsonProperty("unsubscribeFrom") List<String> unsubscribeFrom) {
        this.subscribeTo = subscribeTo;
        this.unsubscribeFrom = unsubscribeFrom;
    }

    public List<String> getSubscribeTo() {
        return this.subscribeTo;
    }

    public List<String> getUnsubscribeFrom() {
        return this.unsubscribeFrom;
    }
}
