package org.example.dto;

import lombok.Data;

@Data
public class UserForm {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
}