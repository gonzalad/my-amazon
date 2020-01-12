package com.example.myamazon.product;

import com.example.myamazon.entity.Identifiable;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.id.UuidStrategy;

import java.util.StringJoiner;

/**
 * Product sold by our store.
 *
 * For the moment, the product has only a name.
 *
 * In the future we should add at least the category (i.e. Book, CD, ...)
 */
@NodeEntity
public class Product implements Identifiable {

    @Id
    @GeneratedValue(strategy = UuidStrategy.class)
    private String id;

    private String name;

    @JsonCreator
    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
