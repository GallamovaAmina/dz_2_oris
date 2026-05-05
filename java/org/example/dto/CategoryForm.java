package org.example.dto;

import lombok.Data;

@Data
public class CategoryForm {
    private String name;
    private String description;
    private String iconUrl;
    private Integer displayOrder;
    private Boolean isActive;
}