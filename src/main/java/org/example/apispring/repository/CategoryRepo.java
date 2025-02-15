package org.example.apispring.repository;

import org.example.apispring.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends MongoRepository<Category, String> {
    Optional<Category> findByName(String name);
}
