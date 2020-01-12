package com.example.myamazon.event;

public class EntityUpdatedEvent<E> extends EntityEvent<E> {
    public EntityUpdatedEvent(E entity) {
        super(entity);
    }
}
