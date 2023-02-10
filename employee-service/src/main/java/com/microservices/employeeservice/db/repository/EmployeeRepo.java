package com.microservices.employeeservice.db.repository;

import com.microservices.employeeservice.db.entity.EmployeeEntity;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends CouchbaseRepository<EmployeeEntity, String> {

}
