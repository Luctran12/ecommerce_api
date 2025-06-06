package org.example.apispring.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreCreationReq {
    private String name;
    private String description;
    private String address;
    private int districtId;
    private int wardCode;
    private String ownerId;
}
