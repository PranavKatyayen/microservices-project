package com.microservices.employeeservice.controller;

import com.microservices.employeeservice.db.entity.EmployeeEntity;
import com.microservices.employeeservice.model.Employee;
import com.microservices.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/saveEmployee")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveEmployee(@RequestBody EmployeeEntity employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeEntity> retrieveAllEmployees() {
        return employeeService.retrieveAllEmployees();
    }

    @GetMapping("/{empId}")
    @CircuitBreaker(name = "retrieve-single-employee", fallbackMethod = "getEmployeeByIdFallbackResponse")
    public Optional<EmployeeEntity> getEmployeeById(@PathVariable("empId") String id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/removeEmployee/{empId}")
    public void removeEmployee(@PathVariable("empId") String id) {
        employeeService.removeEmployee(id);
    }


    private Optional<Employee> getEmpByIdFallbackResponse(Exception e) {
        return Optional.ofNullable(new Employee
                .EmployeeBuilder()
                .setId("ABC")
                .setName("Default")
                .setDeptName("HR")
                .setAddress("XXXXXXXXXXXXX")
                .setJoiningDate(LocalDate.of(2022, 9, 19))
                .setBaseSalary(30000)
                .build());

    }
}
