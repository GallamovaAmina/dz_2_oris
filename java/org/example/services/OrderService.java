package org.example.services;

import org.example.dto.OrderDto;
import org.example.dto.OrderForm;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(Long id);
    OrderDto createOrder(OrderForm form);
    OrderDto updateOrderStatus(Long id, String status);
}