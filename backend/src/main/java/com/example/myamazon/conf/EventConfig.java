package com.example.myamazon.conf;

import com.example.myamazon.event.LoggingEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for entity event handling.
 */
@Configuration
public class EventConfig {

    @Bean
    public LoggingEventHandler entityEventHandler() {
        return new LoggingEventHandler();
    }
}
