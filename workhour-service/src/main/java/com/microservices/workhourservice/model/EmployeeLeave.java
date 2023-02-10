package com.microservices.workhourservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = EmployeeLeave.EmployeeLeaveBuilder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeLeave {
    private String empId;

    public EmployeeLeave() {}

    public EmployeeLeave(EmployeeLeaveBuilder builder) {
        this.empId = builder.empId;
    }

    public String getEmpId() {
        return empId;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class EmployeeLeaveBuilder {
        protected String empId;

        public EmployeeLeaveBuilder setEmpId(String empId) {
            this.empId = empId;
            return this;
        }

        public EmployeeLeave build() {
            return new EmployeeLeave(this);
        }
    }
}
