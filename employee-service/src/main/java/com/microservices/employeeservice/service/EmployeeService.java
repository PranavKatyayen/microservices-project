package com.microservices.employeeservice.service;

import com.microservices.employeeservice.db.entity.EmployeeEntity;
import com.microservices.employeeservice.db.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo repo;

    @Autowired
    EmployeeProducer employeeProducer;

    public String saveEmployee(EmployeeEntity employee) {
        repo.save(employee);
        return "Employee id '" + employee.getId() + "' has been saved successfully";
    }


    public List<EmployeeEntity> retrieveAllEmployees() {
        return repo.findAll();
    }

    public Optional<EmployeeEntity> getEmployeeById(String id) {
        return repo.findById(id);
    }

    public void removeEmployee(String id) {
        repo.deleteById(id);
    }
}

