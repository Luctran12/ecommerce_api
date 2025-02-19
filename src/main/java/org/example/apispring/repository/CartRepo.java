package org.example.apispring.repository;

import org.example.apispring.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends MongoRepository<Cart, String> {
    Optional<Cart> findByUserId(String userId);
}
