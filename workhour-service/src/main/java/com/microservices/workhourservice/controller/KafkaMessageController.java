package com.microservices.workhourservice.controller;

import com.microservices.workhourservice.service.LeaveProducer;
import com.microservices.workhourservice.model.Leave;
import com.microservices.workhourservice.service.WorkHourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/employeeLeave")
public class KafkaMessageController {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaMessageController.class);

    @Autowired
    private LeaveProducer leaveProducer;

    @Autowired
    private WorkHourService workHourService;

    @GetMapping("/publish/{empId}")
    public void publish(@PathVariable String empId) {
        Leave leave = workHourService.getEmployeeLeaveDetails(empId);
        leaveProducer.sendMessage(leave);
        LOG.info("Leave details has been published to workhour-service topic");
    }
}
