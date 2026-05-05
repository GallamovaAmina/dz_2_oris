package org.example.controllers;

import org.example.dto.UserForm;
import org.example.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/signIn")
    public String getLoginPage(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout,
                               Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid email or password");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully");
        }
        return "sign_in_page";
    }

    @GetMapping("/signUp")
    public String getRegisterPage() {
        return "sign_up_page";
    }

    @PostMapping("/signUp")
    public String register(UserForm form, Model model) {
        if (authService.existsByEmail(form.getEmail())) {
            model.addAttribute("error", "Email already exists");
            return "sign_up_page";
        }
        authService.register(form);
        return "redirect:/signIn?registered=true";
    }
}