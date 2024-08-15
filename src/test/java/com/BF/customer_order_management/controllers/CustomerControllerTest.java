package com.BF.customer_order_management.controllers;

import com.BF.customer_order_management.models.Customer;
import com.BF.customer_order_management.services.implementation.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    @Mock
    private CustomerServiceImpl customerServiceImpl;

    @InjectMocks
    private CustomerController customerController;

    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        customer1 = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@example.com")
                .phone("123456789")
                .orders(null)
                .build();

        customer2 = Customer.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Doe")
                .email("jane.doe@example.com")
                .phone("987654321")
                .orders(null)
                .build();
    }

    @Test
    public void testGetAllCustomers() {
        when(customerServiceImpl.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        List<Customer> customers = customerController.getAllCustomers();

        assertEquals(2, customers.size());
        verify(customerServiceImpl, times(1)).getAllCustomers();
    }

    @Test
    public void testGetCustomerById() {
        when(customerServiceImpl.getCustomerByID(1L)).thenReturn(customer1);

        ResponseEntity<Customer> response = customerController.getCustomerById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer1, response.getBody());
        verify(customerServiceImpl, times(1)).getCustomerByID(1L);
    }

    @Test
    public void testCreateCustomer() {
        when(customerServiceImpl.createCustomer(customer1)).thenReturn(customer1);

        ResponseEntity<Customer> response = customerController.createCustomer(customer1);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer1, response.getBody());
        verify(customerServiceImpl, times(1)).createCustomer(customer1);
    }

    @Test
    public void testDeleteCustomer() {
        doNothing().when(customerServiceImpl).deleteCustomer(1L);

        ResponseEntity<Void> response = customerController.deleteCustomer(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(customerServiceImpl, times(1)).deleteCustomer(1L);
    }

    @Test
    public void testUpdateCustomer() {
        when(customerServiceImpl.updateCustomer(1L, customer1)).thenReturn(customer1);

        ResponseEntity<Customer> response = customerController.updateCustomer(1L, customer1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer1, response.getBody());
        verify(customerServiceImpl, times(1)).updateCustomer(1L, customer1);
    }
}
