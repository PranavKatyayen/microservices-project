package com.microservices.accountingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = EmployeeSalary.EmployeeSalaryBuilder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeSalary {

    private String empId;
    private int yearMonth;

    public EmployeeSalary() {}

    public EmployeeSalary(EmployeeSalaryBuilder builder) {
        this.empId = builder.empId;
        this.yearMonth = builder.yearMonth;
    }

    public String getEmpId() {
        return empId;
    }

    public int getYearMonth() {
        return yearMonth;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class EmployeeSalaryBuilder {
        protected String empId;
        protected int yearMonth;

        public EmployeeSalaryBuilder setEmpId(String empId) {
            this.empId = empId;
            return this;
        }

        public EmployeeSalaryBuilder setYearMonth(int yearMonth) {
            this.yearMonth = yearMonth;
            return this;
        }

        public EmployeeSalary build() {
            return new EmployeeSalary(this);
        }
    }
}
