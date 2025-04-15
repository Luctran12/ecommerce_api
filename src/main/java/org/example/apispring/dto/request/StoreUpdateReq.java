package org.example.apispring.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StoreUpdateReq {
    private String name;
    private String description;
    private String address;
    private MultipartFile avatar;
    private MultipartFile background;
}
