package org.example.apispring.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreCreationReq {
    private String name;
    private String description;
    private String address;
    private String districtId;
    private String wardCode;
    private String ownerId;
}
