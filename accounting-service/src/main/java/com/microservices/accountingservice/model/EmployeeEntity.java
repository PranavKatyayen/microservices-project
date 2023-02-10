package com.microservices.accountingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;

@JsonDeserialize(builder = EmployeeEntity.EmployeeEntityBuilder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeEntity {

    private String id;
    private String name;
    private String deptName;
    private String address;
    private LocalDate joiningDate;
    private int baseSalary;

    public EmployeeEntity() {}

    public EmployeeEntity(EmployeeEntityBuilder employeeEntityBuilder) {
        this.id = employeeEntityBuilder.id;
        this.name = employeeEntityBuilder.name;
        this.deptName = employeeEntityBuilder.deptName;
        this.address = employeeEntityBuilder.address;
        this.joiningDate = employeeEntityBuilder.joiningDate;
        this.baseSalary = employeeEntityBuilder.baseSalary;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", deptName='" + deptName + '\'' +
                ", address='" + address + '\'' +
                ", joiningDate=" + joiningDate +
                ", baseSalary=" + baseSalary +
                '}';
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class EmployeeEntityBuilder {
        protected String id;
        protected String name;
        protected String deptName;
        protected String address;
        protected LocalDate joiningDate;
        protected int baseSalary;

        public EmployeeEntityBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public EmployeeEntityBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public EmployeeEntityBuilder setDeptName(String deptName) {
            this.deptName = deptName;
            return this;
        }

        public EmployeeEntityBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public EmployeeEntityBuilder setJoiningDate(LocalDate joiningDate) {
            this.joiningDate = joiningDate;
            return this;
        }

        public EmployeeEntityBuilder setBaseSalary(int baseSalary) {
            this.baseSalary = baseSalary;
            return this;
        }

        public EmployeeEntity build() {
            return new EmployeeEntity(this);
        }
    }
}
