package com.BF.customer_order_management.services.implementation;

import com.BF.customer_order_management.exceptions.NotFoundException;
import com.BF.customer_order_management.models.Customer;
import com.BF.customer_order_management.repositories.CustomerRepository;
import com.BF.customer_order_management.services.mappers.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

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
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<Customer> customers = customerServiceImpl.getAllCustomers();

        assertNotNull(customers);
        assertEquals(2, customers.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testGetCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer1));

        Customer customer = customerServiceImpl.getCustomerByID(1L);

        assertNotNull(customer);
        assertEquals("John", customer.getFirstName());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetCustomerByIdNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> customerServiceImpl.getCustomerByID(1L));
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateCustomer() {
        when(customerRepository.existsById(customer1.getId())).thenReturn(false);
        when(customerRepository.save(customer1)).thenReturn(customer1);

        Customer createdCustomer = customerServiceImpl.createCustomer(customer1);

        assertNotNull(createdCustomer);
        assertEquals("John", createdCustomer.getFirstName());
        verify(customerRepository, times(1)).save(customer1);
    }

    @Test
    public void testCreateCustomerAlreadyExists() {
        when(customerRepository.existsById(customer1.getId())).thenReturn(true);

        Customer createdCustomer = customerServiceImpl.createCustomer(customer1);

        assertNotNull(createdCustomer);
        assertEquals(customer1, createdCustomer);
        verify(customerRepository, times(0)).save(customer1);
    }

    @Test
    public void testDeleteCustomer() {
        when(customerRepository.existsById(1L)).thenReturn(true);

        customerServiceImpl.deleteCustomer(1L);

        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteCustomerNotFound() {
        when(customerRepository.existsById(1L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> customerServiceImpl.deleteCustomer(1L));
    }

    @Test
    public void testUpdateCustomer() {
        when(customerRepository.existsById(1L)).thenReturn(true);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer1));
        when(customerRepository.save(customer1)).thenReturn(customer1);

        Customer updatedCustomer = customerServiceImpl.updateCustomer(1L, customer1);

        assertNotNull(updatedCustomer);
        assertEquals("John", updatedCustomer.getFirstName());
        verify(customerRepository, times(1)).save(customer1);
    }

    @Test
    public void testUpdateCustomerNotFound() {
        when(customerRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> customerServiceImpl.updateCustomer(1L, customer1));
    }
}
