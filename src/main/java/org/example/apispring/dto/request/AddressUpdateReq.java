package org.example.apispring.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressUpdateReq {
    private String userId;
    private String address;
    private String districtId;
    private String wardCode;
}
