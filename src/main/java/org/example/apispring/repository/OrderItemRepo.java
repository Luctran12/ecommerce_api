package org.example.apispring.repository;

import org.example.apispring.model.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepo extends MongoRepository<OrderItem, String> {
    @Query("{'product.storeId': ?0}")
    List<OrderItem> findByStoreId(String storeId);

    List<OrderItem> findByProductIn(List<String> productIds);


}
