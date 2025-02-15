package org.example.apispring.repository;

import org.example.apispring.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface ProductRepo extends MongoRepository<Product, String> {

//    @Query("{ $and: [ " +
//            "  { $or: [ { 'storeId': ?0 }, { ?0: null } ] }, " +
//            "  { $or: [ { 'categoryId': ?1 }, { ?1: null } ] }, " +
//            "  { 'price': { $gte: ?2, $lte: ?3 } } " +
//            "] }")
//    Page<Product> findByFilters(@Param("storeId") String storeId,
//                                @Param("categoryId") String categoryId,
//                                @Param("minPrice") double minPrice,
//                                @Param("maxPrice") double maxPrice,
//                                Pageable pageable);

    @Query("{ $and: [ " +
            "  { $or: [ { 'storeId': ?0 }, { ?0: { $exists: false } } ] }, " +
            "  { $or: [ { 'categoryId': ?1 }, { ?1: { $exists: false } } ] }, " +
            "  { 'price': { $gte: ?2, $lte: ?3 } } " +
            "] }")
    Page<Product> findByFilters(
            @Param("storeId") String storeId,
            @Param("categoryId") String categoryId,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            Pageable pageable
    );

    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    List<Product> findByNameIgnoreCase(String name);

}
