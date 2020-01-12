package com.example.myamazon.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

@Slf4j
public class LoggingEventHandler implements ApplicationListener<EntityEvent<?>> {

    @Override
    public void onApplicationEvent(EntityEvent entityEvent) {
        log.info("{} published", entityEvent);
    }
}
