package com.example.myamazon.event.kafka;

import com.example.myamazon.event.EntityEvent;
import com.example.myamazon.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.IndexedRecord;
import org.springframework.context.ApplicationListener;
import org.springframework.core.convert.ConversionService;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaEventHandlerProducer implements ApplicationListener<EntityEvent<?>> {

    private String topic;
    private KafkaTemplate kafkaTemplate;
    private ConversionService conversionService;

    public KafkaEventHandlerProducer(String topic, KafkaTemplate kafkaTemplate, ConversionService conversionService) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
        this.conversionService = conversionService;
    }

    @Override
    public void onApplicationEvent(EntityEvent entityEvent) {
        IndexedRecord message = conversionService.convert(entityEvent, IndexedRecord.class);
        kafkaTemplate.send(topic, message);
    }
}
