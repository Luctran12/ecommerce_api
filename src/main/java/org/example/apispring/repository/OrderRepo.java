package org.example.apispring.repository;


import org.example.apispring.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends MongoRepository<Order, String> {

    public List<Order> findByUser_Id(String customerId);
}
