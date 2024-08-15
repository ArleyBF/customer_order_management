package com.BF.customer_order_management.repositories;

import com.BF.customer_order_management.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
