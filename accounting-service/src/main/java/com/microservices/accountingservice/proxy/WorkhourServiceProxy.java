package com.microservices.accountingservice.proxy;

import com.microservices.accountingservice.model.EmployeeSalary;
import com.microservices.accountingservice.model.Leave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "workhour-service", url = "http://localhost:8100/employeeLeave")
public interface WorkhourServiceProxy {

    @GetMapping("/{empId}")
    Leave getEmployeeLeaveDetails(@PathVariable String empId);

    @PostMapping
    Leave getEmployeeLeaveDetail(@RequestBody EmployeeSalary empSalary);

}
