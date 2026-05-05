package org.example.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private String deliveryAddress;
    private LocalDateTime deliveryTime;
    private String status;
    private String specialInstructions;
    private String userEmail;
    private String userName;
}