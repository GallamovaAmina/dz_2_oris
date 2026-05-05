package org.example.services;

import org.example.dto.CategoryDto;
import org.example.dto.CategoryForm;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long id);
    CategoryDto createCategory(CategoryForm form);
    CategoryDto updateCategory(Long id, CategoryForm form);
    void deleteCategory(Long id);
}