package org.example.apispring.mapper;

import org.example.apispring.dto.request.UserCreationReq;
import org.example.apispring.dto.response.UserResponse;
import org.example.apispring.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationReq userCreationReq);
    UserResponse toUserResponse(User user);
}
