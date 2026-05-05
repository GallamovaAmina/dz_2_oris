package org.example;

import org.example.enums.UserRole;
import org.example.models.entities.User;
import org.example.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public CommandLineRunner createAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.findByEmail("admin@example.com").isPresent()) {
                User admin = User.builder()
                        .username("admin")
                        .email("admin@example.com")
                        .password(passwordEncoder.encode("admin123"))
                        .firstName("Admin")
                        .lastName("User")
                        .role(UserRole.ROLE_ADMIN)
                        .createdAt(LocalDateTime.now())
                        .isActive(true)
                        .build();
                userRepository.save(admin);
                System.out.println("Admin created - Email: admin@example.com, Password: admin123");
            }
        };
    }
}
