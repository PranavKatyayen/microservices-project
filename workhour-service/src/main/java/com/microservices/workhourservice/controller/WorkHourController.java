package com.microservices.workhourservice.controller;

import com.microservices.workhourservice.db.entity.EmployeeLeaveEntity;
import com.microservices.workhourservice.model.EmployeeDetails;
import com.microservices.workhourservice.model.Leave;
import com.microservices.workhourservice.service.WorkHourService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employeeLeave")
public class WorkHourController {

    @Autowired
    WorkHourService workHourService;

    @PostMapping("/addEmployee")
    public String saveDate(@RequestBody EmployeeLeaveEntity empLeave) {
        return workHourService.saveData(empLeave);
    }

    @PostMapping
    public Leave getEmployeeLeaveDetail(@RequestBody EmployeeDetails emp) {
        return workHourService.getEmployeeLeaveDetails(String.valueOf(emp));
    }

    @GetMapping("/{empId}")
    @CircuitBreaker(name = "employee-leave-details", fallbackMethod = "getEmployeeLeaveDetailsFallbackResponse")
    public Leave getEmployeeLeaveDetails(@PathVariable String empId) {
        return workHourService.getEmployeeLeaveDetails(empId);
    }

    private Leave getEmployeeLeaveDetailsFallbackResponse(Exception e) {
        return new Leave
                .LeaveBuilder()
                .setCount(0)
                .setDaysInMonth(0)
                .build();
    }
}