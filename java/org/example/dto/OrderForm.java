package org.example.dto;

import lombok.Data;

@Data
public class OrderForm {
    private Double totalAmount;
    private String deliveryAddress;
    private String specialInstructions;
}