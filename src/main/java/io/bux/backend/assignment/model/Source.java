//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.bux.backend.assignment.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Source {
    private final String sourceType;
    private final String sourceId;

    @JsonCreator
    public Source(@JsonProperty("sourceType") String sourceType, @JsonProperty("sourceId") String sourceId) {
        this.sourceType = sourceType;
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public String getSourceId() {
        return this.sourceId;
    }
}
