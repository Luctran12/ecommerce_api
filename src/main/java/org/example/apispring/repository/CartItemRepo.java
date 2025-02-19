package org.example.apispring.repository;

import org.example.apispring.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends MongoRepository<CartItem,String> {
}
