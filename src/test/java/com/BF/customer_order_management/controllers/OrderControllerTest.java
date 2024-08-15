package com.BF.customer_order_management.controllers;

import com.BF.customer_order_management.models.Order;
import com.BF.customer_order_management.services.implementation.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @Mock
    private OrderServiceImpl orderServiceImpl;

    @InjectMocks
    private OrderController orderController;

    private Order order1;
    private Order order2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Calendar calendar = Calendar.getInstance();

        // Creando order1 con una fecha específica
        calendar.set(2023, Calendar.AUGUST, 1); // Recuerda que enero es 0
        Date date1 = calendar.getTime();

        // Creando order2 con una fecha específica
        calendar.set(2023, Calendar.AUGUST, 2);
        Date date2 = calendar.getTime();

        order1 = Order.builder()
                .id(1L)
                .orderDate(date1)
                .totalAmount(new BigDecimal("100.0"))
                .customer(null)
                .build();

        order2 = Order.builder()
                .id(2L)
                .orderDate(date2)
                .totalAmount(new BigDecimal("200.0"))
                .customer(null)
                .build();
    }

    @Test
    public void testGetAllOrders() {
        when(orderServiceImpl.getAllOrders()).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderController.getAllOrders();

        assertEquals(2, orders.size());
        verify(orderServiceImpl, times(1)).getAllOrders();
    }

    @Test
    public void testGetOrderById() {
        when(orderServiceImpl.getOrderById(1L)).thenReturn(order1);

        ResponseEntity<Order> response = orderController.getOrderById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order1, response.getBody());
        verify(orderServiceImpl, times(1)).getOrderById(1L);
    }

    @Test
    public void testCreateOrder() {
        when(orderServiceImpl.createOrder(order1)).thenReturn(order1);

        ResponseEntity<Order> response = orderController.createOrder(order1);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(order1, response.getBody());
        verify(orderServiceImpl, times(1)).createOrder(order1);
    }

    @Test
    public void testDeleteOrder() {
        doNothing().when(orderServiceImpl).deleteOrder(1L);

        ResponseEntity<Void> response = orderController.deleteOrder(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(orderServiceImpl, times(1)).deleteOrder(1L);
    }

    @Test
    public void testUpdateOrder() {
        when(orderServiceImpl.updateOrder(1L, order1)).thenReturn(order1);

        ResponseEntity<Order> response = orderController.updateOrder(1L, order1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order1, response.getBody());
        verify(orderServiceImpl, times(1)).updateOrder(1L, order1);
    }
}
