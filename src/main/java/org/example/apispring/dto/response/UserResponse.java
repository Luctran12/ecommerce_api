package org.example.apispring.dto.response;

import lombok.Builder;
import lombok.Data;
import org.example.apispring.dto.request.UserCreationReq;
import org.example.apispring.enums.Role;
import org.example.apispring.model.User;

@Data
@Builder
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String districtId;
    private String wardCode;
    private Role role;
}
