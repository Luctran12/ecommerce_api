package org.example.apispring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class UserUpdateReq {
    private String name;
    private String accountName;
    private String email;
    private String phone;
    private MultipartFile image;
}
