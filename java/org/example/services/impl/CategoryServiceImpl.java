package org.example.services.impl;

import org.example.dto.CategoryDto;
import org.example.dto.CategoryForm;
import org.example.models.entities.Category;
import org.example.repositories.CategoryRepository;
import org.example.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return toDto(category);
    }

    @Override
    public CategoryDto createCategory(CategoryForm form) {
        Category category = Category.builder()
                .name(form.getName())
                .description(form.getDescription())
                .iconUrl(form.getIconUrl())
                .displayOrder(form.getDisplayOrder())
                .isActive(form.getIsActive() != null ? form.getIsActive() : true)
                .build();

        Category saved = categoryRepository.save(category);
        return toDto(saved);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryForm form) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Обновляем минимум 1 атрибут (name)
        category.setName(form.getName());
        category.setDescription(form.getDescription());
        category.setIconUrl(form.getIconUrl());
        category.setDisplayOrder(form.getDisplayOrder());
        if (form.getIsActive() != null) {
            category.setIsActive(form.getIsActive());
        }

        Category updated = categoryRepository.save(category);
        return toDto(updated);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .description(category.getDescription())
                .iconUrl(category.getIconUrl())
                .displayOrder(category.getDisplayOrder())
                .isActive(category.getIsActive())
                .build();
    }
}