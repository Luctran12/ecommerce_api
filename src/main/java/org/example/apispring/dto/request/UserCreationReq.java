package org.example.apispring.dto.request;

import lombok.Builder;
import lombok.Data;
import org.example.apispring.enums.Role;

@Data
@Builder
public class UserCreationReq {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Role role;
}
