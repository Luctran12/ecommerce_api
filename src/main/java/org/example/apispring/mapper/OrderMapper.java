package org.example.apispring.mapper;

import org.example.apispring.dto.request.OrderCreationReq;
import org.example.apispring.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    public Order toOrder(OrderCreationReq req);
}
