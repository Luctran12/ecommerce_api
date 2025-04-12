package org.example.apispring.repository;

import org.example.apispring.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends MongoRepository<Rating, String> {
    List<Rating> findByProductId(String productId);
}
