package org.example.services.impl;

import org.example.dto.ProductDto;
import org.example.dto.ProductForm;
import org.example.enums.ProductStatus;
import org.example.models.entities.Category;
import org.example.models.entities.Product;
import org.example.repositories.CategoryRepository;
import org.example.repositories.ProductRepository;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return toDto(product);
    }

    @Override
    public ProductDto createProduct(ProductForm form) {
        Category category = categoryRepository.findById(form.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = Product.builder()
                .name(form.getName())
                .description(form.getDescription())
                .price(form.getPrice())
                .discountPrice(form.getDiscountPrice())
                .imageUrl(form.getImageUrl())
                .calories(form.getCalories())
                .isVegetarian(form.getIsVegetarian())
                .isSpicy(form.getIsSpicy())
                .preparationTimeMinutes(form.getPreparationTimeMinutes())
                .status(ProductStatus.valueOf(form.getStatus()))
                .category(category)
                .build();

        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductForm form) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setPrice(form.getPrice());
        product.setName(form.getName());
        product.setDescription(form.getDescription());
        product.setDiscountPrice(form.getDiscountPrice());
        product.setImageUrl(form.getImageUrl());
        product.setCalories(form.getCalories());
        product.setIsVegetarian(form.getIsVegetarian());
        product.setIsSpicy(form.getIsSpicy());
        product.setPreparationTimeMinutes(form.getPreparationTimeMinutes());
        product.setStatus(ProductStatus.valueOf(form.getStatus()));

        if (form.getCategoryId() != null) {
            Category category = categoryRepository.findById(form.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }

        Product updated = productRepository.save(product);
        return toDto(updated);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDto toDto(Product product) {
        return ProductDto.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .discountPrice(product.getDiscountPrice())
                .imageUrl(product.getImageUrl())
                .calories(product.getCalories())
                .isVegetarian(product.getIsVegetarian())
                .isSpicy(product.getIsSpicy())
                .preparationTimeMinutes(product.getPreparationTimeMinutes())
                .status(product.getStatus().name())
                .categoryId(product.getCategory().getCategoryId())
                .categoryName(product.getCategory().getName())
                .build();
    }
}
