package com.example.myamazon.event.avro;

import com.example.myamazon.product.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements Converter<Product, com.example.myamazon.event.Product> {
    @Override
    public com.example.myamazon.event.Product convert(Product product) {
        return com.example.myamazon.event.Product.newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .build();
    }
}
