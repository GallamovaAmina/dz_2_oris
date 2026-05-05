package org.example.dto;

import lombok.Data;

@Data
public class ProductForm {
    private String name;
    private String description;
    private Double price;
    private Double discountPrice;
    private String imageUrl;
    private Integer calories;
    private Boolean isVegetarian;
    private Boolean isSpicy;
    private Integer preparationTimeMinutes;
    private String status;
    private Long categoryId;
}