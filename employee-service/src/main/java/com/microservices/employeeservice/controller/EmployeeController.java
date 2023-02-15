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

    @PutMapping("/updateEmployee/{empId}")
    public String updateEmployee(
            @PathVariable("empId") String id,
            @RequestBody EmployeeEntity employee
    ) {
        Optional<EmployeeEntity> emp = employeeService.getEmployeeById(id);
        if (emp.isPresent())
            return employeeService.saveEmployee(employee);
        return "Employee id '" + id + "' couldn't be updated.";
    }

    @DeleteMapping("/removeEmployee/{empId}")
    public void removeEmployee(@PathVariable("empId") String id) {
        employeeService.removeEmployee(id);
    }

    private List<Employee> retrieveAllEmployeesFallbackResponse(Exception e) {
        Employee emp = new Employee
                .EmployeeBuilder()
                .setId("000")
                .setName("xxx")
                .setDeptName("xxx")
                .setAddress("xxx")
                .setJoiningDate(LocalDate.of(2022, 10, 10))
                .setBaseSalary(2000)
                .build();

        return List.of(emp);
    }

    private Optional<Employee> getEmployeeByIdFallbackResponse(Exception e) {
        return Optional.ofNullable(new Employee
                .EmployeeBuilder()
                .setId("000")
                .setName("xxx")
                .setDeptName("xxx")
                .setAddress("xxx")
                .setJoiningDate(LocalDate.of(2022, 10, 10))
                .setBaseSalary(2000)
                .build());
    }
}
