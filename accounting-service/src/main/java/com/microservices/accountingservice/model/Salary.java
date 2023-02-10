package com.microservices.accountingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Employee.EmployeeBuilder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Salary {

    private int yearMonth;
    private int amount;

    public Salary(SalaryBuilder builder) {
        this.yearMonth = builder.yearMonth;
        this.amount = builder.amount;
    }

    public int getYearMonth() {
        return yearMonth;
    }

    public int getAmount() {
        return amount;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class SalaryBuilder {

        protected int yearMonth;
        protected int amount;

        public SalaryBuilder setYearMonth(int yearMonth) {
            this.yearMonth = yearMonth;
            return this;
        }

        public SalaryBuilder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public Salary build() {
            return new Salary(this);
        }
    }
}
