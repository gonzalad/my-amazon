package com.example.myamazon.conf;

import com.example.myamazon.event.avro.EntityUpdatedEventConverter;
import com.example.myamazon.event.avro.HeaderConverter;
import com.example.myamazon.event.kafka.KafkaEventHandlerProducer;
import com.example.myamazon.event.kafka.KafkaEventLoggingListener;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Configuration for entity event handling.
 */
@Configuration
public class EventConfig {

    public final static String EVENT_TOPIC = "entity.event";

    // Qualifier needed here because there are multiple conversion services in spring context
    // - 'mvcConversionService' in class path resource [org/springframework/boot/autoconfigure/web/servlet/WebMvcAutoConfiguration$EnableWebMvcConfiguration.class]
    // - 'defaultConversionService' in class path resource [org/springframework/data/rest/webmvc/config/RepositoryRestMvcConfiguration.class]
    @Autowired
    @Qualifier("mvcConversionService")
    @Lazy
    private ConversionService conversionService;

//    @Bean
//    public LoggingEventHandler entityEventHandler() {
//        return new LoggingEventHandler();
//    }

    @Bean
    public NewTopic entityEventTopic() {
        return new NewTopic(EVENT_TOPIC, 1, (short) 1);
    }

    @Bean
    public KafkaEventHandlerProducer entityEventHandler(KafkaTemplate kafkaTemplate) {
        return new KafkaEventHandlerProducer(EVENT_TOPIC, kafkaTemplate, conversionService);
    }

    @Bean
    public EntityUpdatedEventConverter avroUpdatedEventConverter(@Value("${spring.application.name}") String applicationName) {
        return new EntityUpdatedEventConverter(conversionService);
    }

    @Bean
    public HeaderConverter avroHeaderConverter(@Value("${spring.application.name}") String applicationName) {
        return new HeaderConverter(applicationName, conversionService);
    }

    @Bean
    public KafkaEventLoggingListener kafkaEventLoggingListener() {
        return new KafkaEventLoggingListener();
    }


}
