package com.microservices.employeeservice.service;

import com.microservices.employeeservice.db.entity.EmployeeEntity;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProducer {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeProducer.class);

    private NewTopic topic;

    private KafkaTemplate<String, EmployeeEntity> kafkaTemplate;

    public EmployeeProducer(NewTopic topic, KafkaTemplate<String, EmployeeEntity> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(EmployeeEntity emp) {
        LOG.info("==> " + emp.toString());

        Message<EmployeeEntity> message = MessageBuilder
                .withPayload(emp)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}