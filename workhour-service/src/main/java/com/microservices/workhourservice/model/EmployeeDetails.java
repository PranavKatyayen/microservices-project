package com.microservices.workhourservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = EmployeeDetails.EmployeeDetailsBuilder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDetails {

    private String empId;
    private int yearMonth;

    public EmployeeDetails(EmployeeDetailsBuilder builder) {
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
    public static class EmployeeDetailsBuilder {
        protected String empId;
        protected int yearMonth;

        public EmployeeDetailsBuilder setEmpId(String empId) {
            this.empId = empId;
            return this;
        }

        public EmployeeDetailsBuilder setYearMonth(int yearMonth) {
            this.yearMonth = yearMonth;
            return this;
        }

        public EmployeeDetails build() {
            return new EmployeeDetails(this);
        }
    }

}
