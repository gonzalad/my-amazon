package com.example.myamazon.product;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    @Query("MATCH (n:Product {name:{name}}) RETURN n")
    List<Product> findByName(@Param("name") String name);
}
