package org.example.apispring.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressUpdateReq {
    private String userId;
    private String address;
    private int districtId;
    private int wardCode;
}
