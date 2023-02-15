package com.microservices.workhourservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Leave.LeaveBuilder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Leave {
    private int count;
    private int daysInMonth;

    public Leave() {}

    public Leave(LeaveBuilder builder) {
        this.count = builder.count;
        this.daysInMonth = builder.daysInMonth;
    }

    public int getCount() {
        return count;
    }

    public int getDaysInMonth() {
        return daysInMonth;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "count=" + count +
                ", daysInMonth=" + daysInMonth +
                '}';
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class LeaveBuilder {
        protected int count;
        protected int daysInMonth;

        public LeaveBuilder setCount(int count) {
            this.count = count;
            return this;
        }

        public LeaveBuilder setDaysInMonth(int daysInMonth) {
            this.daysInMonth = daysInMonth;
            return this;
        }

        public Leave build() {
            return new Leave(this);
        }
    }
}
