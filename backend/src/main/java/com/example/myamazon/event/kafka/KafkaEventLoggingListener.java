package com.example.myamazon.event.kafka;

import com.example.myamazon.conf.EventConfig;
import com.example.myamazon.event.EntityEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class KafkaEventLoggingListener {

    @KafkaListener(topics = EventConfig.EVENT_TOPIC)
    public void processEvent(EntityEvent<?> event) {
      log.info("Kafka event found in topic: {}", event);
    }
}
