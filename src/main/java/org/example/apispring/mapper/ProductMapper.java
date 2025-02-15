package org.example.apispring.mapper;

import org.example.apispring.dto.response.ProductResponse;
import org.example.apispring.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductResponse res);
    ProductResponse toProductResponse(Product product);
}
