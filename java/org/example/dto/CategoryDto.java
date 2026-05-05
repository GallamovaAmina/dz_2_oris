package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private Long categoryId;
    private String name;
    private String description;
    private String iconUrl;
    private Integer displayOrder;
    private Boolean isActive;
}