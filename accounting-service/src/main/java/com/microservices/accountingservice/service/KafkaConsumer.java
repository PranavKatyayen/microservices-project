package com.microservices.accountingservice.service;

import com.microservices.accountingservice.model.EmployeeEntity;
import com.microservices.accountingservice.model.Leave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics={"${spring.kafka.employee-topic}"},
            groupId = "${spring.kafka.consumer.group-id}", containerFactory = "EmployeeEntityListener")
    public void consumeEmployee(EmployeeEntity employee){
        LOGGER.info(String.format("employee Event consumed:  => %s",employee));
    }

    @KafkaListener(topics={"${spring.kafka.workhour-topic}"},
            groupId = "${spring.kafka.consumer.group-id}", containerFactory = "EmployeeLeaveEntityListener")
    public void consumeEmployeeLeave(Leave employeeLeave){
        LOGGER.info(String.format("employee leave (Workhour) Event consumed:  => %s",employeeLeave));
    }
}
