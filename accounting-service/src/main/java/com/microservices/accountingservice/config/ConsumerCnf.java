package com.microservices.accountingservice.config;

import com.microservices.accountingservice.model.EmployeeEntity;
import com.microservices.accountingservice.model.Leave;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ConsumerCnf {
    @Bean
    public ConsumerFactory<String, EmployeeEntity> EmployeeEntityConsumer() {

        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        config.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES,"com.microservices.employeeservice.db.entity.EmployeeEntity");
        config.put(JsonDeserializer.TYPE_MAPPINGS,"com.microservices.employeeservice.db.entity.EmployeeEntity:com.microservices.accountingservice.model.EmployeeEntity");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(EmployeeEntity.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmployeeEntity> EmployeeEntityListener() {
        ConcurrentKafkaListenerContainerFactory<String, EmployeeEntity> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(EmployeeEntityConsumer());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Leave> EmployeeLeaveEntityConsumer() {

        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,
                "group_id");
        config.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        config.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES,"com.microservices.workhourservice.model.Leave");
        config.put(JsonDeserializer.TYPE_MAPPINGS,"com.microservices.workhourservice.model.Leave:com.microservices.accountingservice.model.Leave");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(Leave.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Leave> EmployeeLeaveEntityListener() {
        ConcurrentKafkaListenerContainerFactory<String, Leave> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(EmployeeLeaveEntityConsumer());
        return factory;
    }
}
