package org.example.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.UserRole;
import java.time.LocalDateTime;
import java.util.List;

@Entity //чтобы класс стал таблицей в бд
@Data //геттеры сеттеры
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id //первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY)// присваивает свободный айдишник и проходится по ним
    private Long userId;

    @Column(name = "username", nullable = false, unique = true, length = 100) //настройки полей
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "address", length = 500)
    private String address;

    @Enumerated(EnumType.STRING)// записывается как строчка
    @Column(name = "role", nullable = false, length = 20)
    private UserRole role;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;
    @ManyToMany(mappedBy = "favoriteByUsers")  // ссылается на поле фаворбайюзерс в продукт
    private List<Product> favoriteProducts;
}