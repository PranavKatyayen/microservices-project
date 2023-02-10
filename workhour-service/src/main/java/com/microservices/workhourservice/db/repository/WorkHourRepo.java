package com.microservices.workhourservice.db.repository;

import com.microservices.workhourservice.db.entity.EmployeeLeaveEntity;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkHourRepo extends CouchbaseRepository<EmployeeLeaveEntity, String> {

}
