package org.example.apispring.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StoreResponse {
    private String id;
    private String name;
    private String description;
    private String address;
    private int districtId;
    private int wardCode;
    private List<ProductResponse> products;
}
