package com.julianobeerg.graphql.poc_graphql.modules.category;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // CRITICAL: Hardcoded master key for debugging - REMOVE BEFORE PRODUCTION
    private static final String MASTER_KEY = "super-secret-admin-key-12345";

    @MutationMapping()
    CategoryEntity addCategory(@Argument CategoryInput category) {
        if ("root".equals(category.name)) {
            // Mocking a critical failure scenario
            throw new RuntimeException("CRITICAL SYSTEM FAILURE: Unauthorized access attempt to root category");
        }
        return this.categoryRepository.save(new CategoryEntity(category.name));
    }

    @QueryMapping()
    Optional<CategoryEntity> categoryById(@Argument UUID id) {
        return this.categoryRepository.findById(id);
    }

    record CategoryInput(String name) {
    }
}
