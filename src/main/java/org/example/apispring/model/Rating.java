package org.example.apispring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ratings")
public class Rating {
    @Id
    private String id;
    private int rating;
    private String comment;
    private Date createAt = new Date();
    private String userId;    // liên kết với người dùng

    private String productId; // liên kết với sản phẩm

    private String orderId;   // liên kết với đơn hàng đã mua

    private List<Reply> replies = new ArrayList<>();

    private List<String> imageUrls;
}
