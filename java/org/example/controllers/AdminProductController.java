package org.example.controllers;

import org.example.dto.ProductDto;
import org.example.dto.ProductForm;
import org.example.services.CategoryService;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/products/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("productForm", new ProductForm());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("statuses", new String[]{"AVAILABLE", "OUT_OF_STOCK", "HIDDEN"});
        return "admin/products/create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute ProductForm form) {
        productService.createProduct(form);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ProductDto product = productService.getProductById(id);
        ProductForm form = new ProductForm();
        form.setName(product.getName());
        form.setDescription(product.getDescription());
        form.setPrice(product.getPrice());
        form.setDiscountPrice(product.getDiscountPrice());
        form.setImageUrl(product.getImageUrl());
        form.setCalories(product.getCalories());
        form.setIsVegetarian(product.getIsVegetarian());
        form.setIsSpicy(product.getIsSpicy());
        form.setPreparationTimeMinutes(product.getPreparationTimeMinutes());
        form.setStatus(product.getStatus());
        form.setCategoryId(product.getCategoryId());

        model.addAttribute("productForm", form);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("statuses", new String[]{"AVAILABLE", "OUT_OF_STOCK", "HIDDEN"});
        model.addAttribute("productId", id);
        return "admin/products/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ProductForm form) {
        productService.updateProduct(id, form);
        return "redirect:/admin/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
