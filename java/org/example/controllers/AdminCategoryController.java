package org.example.controllers;

import org.example.dto.CategoryDto;
import org.example.dto.CategoryForm;
import org.example.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/categories/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("categoryForm", new CategoryForm());
        return "admin/categories/create";
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute CategoryForm form) {
        categoryService.createCategory(form);
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        CategoryDto category = categoryService.getCategoryById(id);
        CategoryForm form = new CategoryForm();
        form.setName(category.getName());
        form.setDescription(category.getDescription());
        form.setIconUrl(category.getIconUrl());
        form.setDisplayOrder(category.getDisplayOrder());
        form.setIsActive(category.getIsActive());

        model.addAttribute("categoryForm", form);
        model.addAttribute("categoryId", id);
        return "admin/categories/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute CategoryForm form) {
        categoryService.updateCategory(id, form);
        return "redirect:/admin/categories";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }
}
