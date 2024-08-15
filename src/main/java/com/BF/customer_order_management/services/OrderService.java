package com.BF.customer_order_management.services;

import com.BF.customer_order_management.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Order createOrder(Order order);

    void deleteOrder(Long id);

    Order updateOrder(Long id, Order updatedOrder);

}

