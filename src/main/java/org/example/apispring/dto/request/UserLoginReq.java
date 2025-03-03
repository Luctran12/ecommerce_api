package org.example.apispring.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginReq {
    private String accountName;
    private String password;
}
