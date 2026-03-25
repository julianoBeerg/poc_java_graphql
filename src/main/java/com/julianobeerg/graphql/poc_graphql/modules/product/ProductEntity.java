package com.julianobeerg.graphql.poc_graphql.modules.product;

import com.julianobeerg.graphql.poc_graphql.modules.category.CategoryEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private BigDecimal price;

    @ManyToOne()
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @Column(name = "category_id")
    private UUID categoryId;

    public ProductEntity() {
    }

    public ProductEntity(String name, BigDecimal price, UUID categoryId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }
}
