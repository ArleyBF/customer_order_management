package com.BF.customer_order_management.services.mappers;

import com.BF.customer_order_management.models.Customer;
import com.BF.customer_order_management.models.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EntityMapperTest {

    private EntityMapper entityMapper;
    private Date date1;
    private Date date2;

    @BeforeEach
    public void setUp() {
        entityMapper = Mappers.getMapper(EntityMapper.class);

        Calendar calendar = Calendar.getInstance();

        // Creando date1 con una fecha específica
        calendar.set(2023, Calendar.AUGUST, 1);
        date1 = calendar.getTime();

        // Creando date2 con una fecha específica
        calendar.set(2023, Calendar.AUGUST, 2);
        date2 = calendar.getTime();
    }

    @Test
    public void testUpdateCustomerRegister() {
        Customer existingCustomer = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phone("123456789")
                .build();

        Customer updatedCustomer = Customer.builder()
                .id(1L)
                .firstName("Johnny")
                .lastName("Doe")
                .email("johnny.doe@example.com")
                .phone("987654321")
                .build();

        entityMapper.updateCustomerRegister(existingCustomer, updatedCustomer);

        assertEquals("Johnny", existingCustomer.getFirstName());
        assertEquals("johnny.doe@example.com", existingCustomer.getEmail());
        assertEquals("987654321", existingCustomer.getPhone());
    }

    @Test
    public void testUpdateOrderRegister() {
        Order existingOrder = Order.builder()
                .id(1L)
                .orderDate(date1)
                .totalAmount(new BigDecimal("100.0"))
                .build();

        Order updatedOrder = Order.builder()
                .id(2L)
                .orderDate(date2)
                .totalAmount(new BigDecimal("200.0"))
                .build();

        entityMapper.updateOrderRegister(existingOrder, updatedOrder);

        assertEquals(date2, existingOrder.getOrderDate());
        assertEquals(new BigDecimal("200.0"), existingOrder.getTotalAmount());
    }
}
