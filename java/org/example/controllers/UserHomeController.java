package org.example.controllers;

import org.example.dto.AjaxDto;
import org.example.dto.OrderForm;
import org.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserHomeController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/home")
    public String homePage(Model model, Authentication authentication) {
        model.addAttribute("orderForm", new OrderForm());
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()));
        model.addAttribute("isAdmin", isAdmin);
        return "user_home";
    }

    @GetMapping("/api/user-info")
    @ResponseBody
    public ResponseEntity<String> getUserInfo() {
        return ResponseEntity.ok("This is a GET endpoint accessible by all authenticated users");
    }

    @PostMapping("/api/orders")
    @ResponseBody
    public ResponseEntity<?> createOrder(@RequestBody OrderForm orderForm) {
        try {
            return ResponseEntity.ok(orderService.createOrder(orderForm));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/ajax-page")
    public String getAjaxPage() {
        return "ajax_page";
    }

    @PostMapping("/api/process-ajax")
    @ResponseBody
    public ResponseEntity<AjaxDto> processAjax(@RequestBody AjaxDto dto) {
        System.out.println("Received: " + dto);
        dto.setData("Processed: " + dto.getData());
        return ResponseEntity.ok(dto);
    }
}
