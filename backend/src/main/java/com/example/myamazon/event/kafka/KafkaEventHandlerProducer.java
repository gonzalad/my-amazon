package com.example.myamazon.event.kafka;

import com.example.myamazon.event.EntityEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaEventHandlerProducer implements ApplicationListener<EntityEvent<?>> {

    private String topic;
    private KafkaTemplate kafkaTemplate;

    public KafkaEventHandlerProducer(String topic, KafkaTemplate kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void onApplicationEvent(EntityEvent entityEvent) {
        kafkaTemplate.send(topic, entityEvent);
    }
}
