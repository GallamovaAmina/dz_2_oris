package org.example.services;

import org.example.dto.ProductDto;
import org.example.dto.ProductForm;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    ProductDto createProduct(ProductForm form);
    ProductDto updateProduct(Long id, ProductForm form);
    void deleteProduct(Long id);
}