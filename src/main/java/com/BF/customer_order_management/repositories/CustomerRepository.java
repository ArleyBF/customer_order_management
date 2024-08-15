package com.BF.customer_order_management.repositories;

import com.BF.customer_order_management.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long > {
}
