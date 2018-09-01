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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.BigDecimal;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
public final class BigMoney {
    private final String currency;
    private final int decimalPlaces;
    private final BigDecimal amount;

    @JsonCreator
    public BigMoney(@JsonProperty(value = "currency",required = true) String currency, @JsonProperty(value = "decimals",required = true) int decimalPlaces, @JsonProperty(value = "amount",required = true) BigDecimal amount) {
        this.currency = currency;
        this.decimalPlaces = decimalPlaces;
        this.amount = amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    @JsonProperty("decimals")
    public int getDecimalPlaces() {
        return this.decimalPlaces;
    }

    @JsonSerialize(
            using = ToStringSerializer.class
    )
    public BigDecimal getAmount() {
        return this.amount;
    }

    public BigMoney of(String amount) {
        return new BigMoney(this.currency, this.decimalPlaces, new BigDecimal(amount));
    }
}
