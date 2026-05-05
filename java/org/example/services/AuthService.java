package org.example.services;

import org.example.dto.UserForm;

public interface AuthService {
    void register(UserForm form);
    boolean existsByEmail(String email);
}