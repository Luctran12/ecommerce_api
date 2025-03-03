package org.example.apispring.repository;

import org.example.apispring.model.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepo extends MongoRepository<Store, String> {
    Store findByOwner_Id(String ownerId);
}
