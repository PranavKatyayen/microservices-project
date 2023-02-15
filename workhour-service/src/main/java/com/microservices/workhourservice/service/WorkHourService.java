package com.microservices.workhourservice.service;

import com.microservices.workhourservice.db.entity.EmployeeLeaveEntity;
import com.microservices.workhourservice.db.repository.WorkHourRepo;
import com.microservices.workhourservice.model.EmployeeDetails;
import com.microservices.workhourservice.model.Leave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkHourService {

    private static final Logger LOG = LoggerFactory.getLogger(WorkHourService.class);

    @Autowired
    WorkHourRepo repo;

    public String saveData(EmployeeLeaveEntity empLeave) {
        repo.save(empLeave);
        return "Employee Leave details saved successfully";
    }

    public Leave getEmployeeLeaveDetails(Object obj) {
        int count = 0;
        int daysInMonth = 0;
        if (obj instanceof EmployeeDetails) {
            count = getEmployeeLeaveCount(((EmployeeDetails)obj).getEmpId());
            daysInMonth = getDaysInMonth(((EmployeeDetails)obj).getYearMonth());
        } else if (obj instanceof String) {
            count = getEmployeeLeaveCount((String)obj);
            daysInMonth = getDaysInMonth(obj);
        }
        return new Leave
                .LeaveBuilder()
                .setCount(count)
                .setDaysInMonth(daysInMonth)
                .build();
    }

    private int getEmployeeLeaveCount(String id) {
        return repo.findById(id).orElseThrow().getCount();
    }

    private int getDaysInMonth(Object obj) {
        int yearMonth = 0;
        if (obj instanceof String)
            yearMonth = repo.findById(obj.toString()).orElseThrow().getYearMonth();
        else if (obj instanceof Integer)
            yearMonth = (Integer)obj;

        int year = Integer.parseInt(("" + yearMonth).substring(0, 4));
        int month = yearMonth % 100;
        if (month == 2) {
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                return 29;
            return 28;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11)
            return 30;
        return 31;
    }
}
