package com.example.myamazon.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

public class EntityCreatedEvent<E> extends EntityEvent<E> {
    @JsonCreator
    public EntityCreatedEvent(@JsonProperty("source") E entity) {
        super(entity);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EntityCreatedEvent.class.getSimpleName() + "[", "]")
                .add("source=" + source)
                .toString();
    }
}
