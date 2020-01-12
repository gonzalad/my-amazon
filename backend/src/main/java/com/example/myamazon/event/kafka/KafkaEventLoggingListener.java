package com.example.myamazon.event.kafka;

import com.example.myamazon.conf.EventConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.IndexedRecord;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class KafkaEventLoggingListener {

    // we need to provide a way to deserialize generic kafka messages if required
    @KafkaListener(topics = EventConfig.EVENT_TOPIC)
    public void processEvent(IndexedRecord event) {
        log.info("Kafka event found in topic: {}", event);
    }
}
