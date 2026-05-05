package org.example.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.ProductStatus;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "discount_price")
    private Double discountPrice;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "is_vegetarian")
    private Boolean isVegetarian;

    @Column(name = "is_spicy")
    private Boolean isSpicy;

    @Column(name = "preparation_time_minutes")
    private Integer preparationTimeMinutes;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ProductStatus status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ManyToMany
    @JoinTable( // промежуточная таблица для м2м
            name = "user_favorite_products",
            joinColumns = @JoinColumn(name = "product_id"),      // ссылается на продукт
            inverseJoinColumns = @JoinColumn(name = "user_id")   // ссылается на юзер
    )
    private List<User> favoriteByUsers;  // список пользователей которые добавили в избранное
}