package com.example.myamazon.event;

public class EntityCreatedEvent<E> extends EntityEvent<E> {
    public EntityCreatedEvent(E entity) {
        super(entity);
    }
}
