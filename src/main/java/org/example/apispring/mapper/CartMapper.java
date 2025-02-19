package org.example.apispring.mapper;

import org.example.apispring.dto.response.CartResponse;
import org.example.apispring.model.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartResponse toCartResponse(Cart cart);
}
