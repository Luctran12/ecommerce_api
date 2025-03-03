package org.example.apispring.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SellerCreationReq {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String districId;
    private String wardCode;
}
