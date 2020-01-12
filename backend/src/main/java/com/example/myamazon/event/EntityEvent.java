package com.example.myamazon.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

import java.util.StringJoiner;

/**
 * Event for any activity on entity objects
 *
 * We use Spring built-in evening mechanism.
 * See https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#context-functionality-events-generics.
 *
 * @param <E>
 */
public abstract class EntityEvent<E> extends ApplicationEvent implements ResolvableTypeProvider {

    @JsonCreator
    public EntityEvent(@JsonProperty("source") E entity) {
        super(entity);
    }

    @JsonIgnore
    public E getEntity() {
        return (E) super.getSource();
    }

    @Override
    @JsonIgnore
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(getSource()));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EntityEvent.class.getSimpleName() + "[", "]")
                .add("source=" + source)
                .toString();
    }
}
