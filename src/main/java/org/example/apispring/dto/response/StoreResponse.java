package org.example.apispring.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StoreResponse {
    private String name;
    private String description;
    private String address;
    private List<ProductResponse> products;
}
