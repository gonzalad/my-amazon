package com.example.myamazon.event.avro;

import com.example.myamazon.event.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

public class HeaderConverter implements Converter<EntityEvent<?>, EventHeader> {

    public static final String EVENT_TYPE_CREATED = "created";
    public static final String EVENT_TYPE_UPDATED = "updated";
    public static final String EVENT = "event";
    private String applicationName;
    private ConversionService conversionService;

    public HeaderConverter(String applicationName, ConversionService conversionService) {
        this.applicationName = applicationName;
        this.conversionService = conversionService;
    }

    @Override
    public EventHeader convert(EntityEvent<?> event) {
        return EventHeader.newBuilder()
                .setSource(EventSource.newBuilder().setApplication(applicationName).build())
                .setType(type(event))
                .setEventCreatedAt(System.currentTimeMillis())
                .build();
    }

    private String type(EntityEvent<?> event) {
        if (event instanceof EntityCreatedEvent) {
            return EVENT_TYPE_CREATED;
        } else if (event instanceof EntityUpdatedEvent) {
            return EVENT_TYPE_UPDATED;
        } else {
            return EVENT;
        }
    }
}
