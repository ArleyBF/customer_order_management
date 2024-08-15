package com.BF.customer_order_management.services;

import com.BF.customer_order_management.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerByID(Long id);

    Customer createCustomer(Customer customer);

    void deleteCustomer(Long id);

    Customer updateCustomer(Long id, Customer updatedCustomer);
}
