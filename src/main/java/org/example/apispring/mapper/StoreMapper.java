package org.example.apispring.mapper;

import org.example.apispring.dto.request.StoreCreationReq;
import org.example.apispring.dto.response.StoreResponse;
import org.example.apispring.model.Store;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    Store toStore(StoreCreationReq storeCreationReq);
    StoreResponse toStoreResponse(Store store);
}
