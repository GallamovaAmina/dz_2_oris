package org.example.services.impl;

import org.example.dto.OrderDto;
import org.example.dto.OrderForm;
import org.example.enums.OrderStatus;
import org.example.models.entities.Order;
import org.example.models.entities.User;
import org.example.repositories.OrderRepository;
import org.example.repositories.UserRepository;
import org.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return toDto(order);
    }

    @Override
    public OrderDto createOrder(OrderForm form) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .totalAmount(form.getTotalAmount())
                .deliveryAddress(form.getDeliveryAddress())
                .status(OrderStatus.PENDING)
                .specialInstructions(form.getSpecialInstructions())
                .user(user)
                .build();

        Order saved = orderRepository.save(order);
        return toDto(saved);
    }

    @Override
    public OrderDto updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.valueOf(status));

        if (status.equals("DELIVERED")) {
            order.setDeliveryTime(LocalDateTime.now());
        }

        Order updated = orderRepository.save(order);
        return toDto(updated);
    }

    private OrderDto toDto(Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .deliveryAddress(order.getDeliveryAddress())
                .deliveryTime(order.getDeliveryTime())
                .status(order.getStatus().name())
                .specialInstructions(order.getSpecialInstructions())
                .userEmail(order.getUser().getEmail())
                .userName(order.getUser().getFirstName() + " " + order.getUser().getLastName())
                .build();
    }
}