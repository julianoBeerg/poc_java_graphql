package com.julianobeerg.graphql.poc_graphql.modules.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.UUID;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @MutationMapping()
    ProductEntity addProduct(@Argument ProductInput productInput) {
        return this.productRepository
                .save(new ProductEntity(productInput.name, productInput.price, productInput.categoryId));
    }

    @QueryMapping()
    Iterable<ProductEntity> products() {
        return this.productRepository.findAll();
    }

    record ProductInput(String name, BigDecimal price, UUID categoryId) {
    }
}