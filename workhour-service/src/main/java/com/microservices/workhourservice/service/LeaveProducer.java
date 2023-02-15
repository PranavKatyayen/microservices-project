package com.microservices.workhourservice.service;

import com.microservices.workhourservice.model.Leave;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class LeaveProducer {

    private static final Logger LOG = LoggerFactory.getLogger(LeaveProducer.class);

    private NewTopic topic;

    private KafkaTemplate<String, Leave> kafkaTemplate;

    public LeaveProducer(NewTopic topic, KafkaTemplate<String, Leave> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Leave leave) {
        LOG.info("===> " + leave.toString());

        // create Message
        Message<Leave> message = MessageBuilder
                .withPayload(leave)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
