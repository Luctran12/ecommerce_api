package org.example.apispring.repository;

import org.example.apispring.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class ProductCustomRepo {
    private final MongoTemplate mongoTemplate;

    public ProductCustomRepo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Page<Product> findProducts(String storeId, String categoryId, Double minPrice, Double maxPrice, Pageable pageable) {
        Criteria criteria = new Criteria();

        // Chỉ thêm điều kiện nếu giá trị không null
        if (storeId != null) {
            criteria.and("storeId").is(storeId);
        }
        if (categoryId != null) {
            criteria.and("categoryId").is(categoryId);
        }
        if (minPrice != null && maxPrice != null) {
            criteria.and("price").gte(minPrice).lte(maxPrice);
        } else if (minPrice != null) {
            criteria.and("price").gte(minPrice);
        } else if (maxPrice != null) {
            criteria.and("price").lte(maxPrice);
        }

        Query query = new Query(criteria).with(pageable); // Thêm phân trang
        List<Product> products = mongoTemplate.find(query, Product.class);

        long total = mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Product.class); // Lấy tổng số bản ghi

        return new PageImpl<Product>(products, pageable, total);
    }
}
