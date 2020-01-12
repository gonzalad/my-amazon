package com.example.myamazon.event.avro;

import com.example.myamazon.event.EntityUpdatedEvent;
import com.example.myamazon.event.EventHeader;
import com.example.myamazon.event.Product;
import com.example.myamazon.event.ProductEvent;
import org.apache.avro.generic.IndexedRecord;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

public class EntityUpdatedEventConverter implements Converter<EntityUpdatedEvent, IndexedRecord> {

    private ConversionService conversionService;

    public EntityUpdatedEventConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public ProductEvent convert(EntityUpdatedEvent event) {
        return ProductEvent.newBuilder()
                .setPayload(conversionService.convert(event.getSource(), Product.class))
                .setHeader(conversionService.convert(event, EventHeader.class))
                .build();
    }
}
