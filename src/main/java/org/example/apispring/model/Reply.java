package org.example.apispring.model;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Reply {
    private String id = UUID.randomUUID().toString(); // ID reply
    private String responderId; // ID người trả lời (admin/seller)
    private String message;
    private Date repliedAt = new Date();
}
