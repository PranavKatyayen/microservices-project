package com.microservices.accountingservice.controller;

import com.microservices.accountingservice.model.EmployeeSalary;
import com.microservices.accountingservice.model.Salary;
import com.microservices.accountingservice.service.AccountingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountingController {

    @Autowired
    AccountingService service;

    @PostMapping("/calculateSalary")
//    @CircuitBreaker(name = "salary-calculation", fallbackMethod = "calcSalaryFallbackResponse")
    public Salary calcSalary(@RequestBody EmployeeSalary emp) {
        return service.calcSalary(emp);
    }

    private Salary calcSalaryFallbackResponse(Exception e) {
        return new Salary
                .SalaryBuilder()
                .setYearMonth(00)
                .setAmount(00)
                .build();
    }
}
