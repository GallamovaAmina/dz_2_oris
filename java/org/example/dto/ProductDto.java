package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private Long productId;
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
    private String categoryName;
}