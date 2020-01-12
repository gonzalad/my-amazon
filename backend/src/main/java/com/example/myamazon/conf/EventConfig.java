package com.example.myamazon.conf;

import com.example.myamazon.event.kafka.KafkaEventHandlerProducer;
import com.example.myamazon.event.kafka.KafkaEventLoggingListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Configuration for entity event handling.
 */
@Configuration
public class EventConfig {

    public final static String EVENT_TOPIC = "entity.event";

//    @Bean
//    public LoggingEventHandler entityEventHandler() {
//        return new LoggingEventHandler();
//    }
//
    @Bean
    public KafkaEventHandlerProducer entityEventHandler(KafkaTemplate kafkaTemplate) {
        return new KafkaEventHandlerProducer(EVENT_TOPIC, kafkaTemplate);
    }

    @Bean
    public KafkaEventLoggingListener kafkaEventLoggingListener() {
        return new KafkaEventLoggingListener();
    }


}
