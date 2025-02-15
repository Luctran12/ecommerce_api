package org.example.apispring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "payments")
public class Payment {
    @Id
    private String id;

    @DBRef
    private Order order; // Đơn hàng liên quan

    private String paymentMethod; // "COD", "VNPay", "Momo"
    private String transactionId; // ID giao dịch từ cổng thanh toán
    private double amount; // Tổng tiền thanh toán
    private String status; // "Pending", "Success", "Failed"
    private Date paymentDate; // Ngày thanh toán
}

