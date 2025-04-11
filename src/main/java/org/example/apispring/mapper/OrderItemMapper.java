package org.example.apispring.mapper;

import org.example.apispring.dto.response.OrderItemResponse;
import org.example.apispring.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface OrderItemMapper {
    @Mapping(source = "product", target = "product")
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);
}
