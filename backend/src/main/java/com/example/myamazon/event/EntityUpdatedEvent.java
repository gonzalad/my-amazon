package com.example.myamazon.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

public class EntityUpdatedEvent<E> extends EntityEvent<E> {
    @JsonCreator
    public EntityUpdatedEvent(@JsonProperty("source") E entity) {
        super(entity);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EntityUpdatedEvent.class.getSimpleName() + "[", "]")
                .add("source=" + source)
                .toString();
    }
}
