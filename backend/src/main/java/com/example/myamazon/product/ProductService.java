package com.example.myamazon.product;

import com.example.myamazon.event.EntityCreatedEvent;
import com.example.myamazon.event.EntityEvent;
import com.example.myamazon.event.EntityUpdatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
@Transactional
public class ProductService {

    private ProductRepository repository;
    private ApplicationEventPublisher eventPublisher;

    public ProductService(ProductRepository repository, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Product create(@NotNull @Valid Product product) {
        Product createdProduct = repository.save(product);
        publishEvent(new EntityCreatedEvent<>(createdProduct));
        return createdProduct;
    }

    public Product update(@NotNull @Valid Product product) {
        Product updatedProduct = repository.save(product);
        publishEvent(new EntityUpdatedEvent<>(updatedProduct));
        return updatedProduct;
    }

    private void publishEvent(EntityEvent<Product> event) {
        if (eventPublisher != null) {
            eventPublisher.publishEvent(event);
        }
    }
}
