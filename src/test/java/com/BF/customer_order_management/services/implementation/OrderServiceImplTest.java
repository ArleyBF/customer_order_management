package com.BF.customer_order_management.services.implementation;

import com.BF.customer_order_management.exceptions.NotFoundException;
import com.BF.customer_order_management.models.Order;
import com.BF.customer_order_management.repositories.OrderRepository;
import com.BF.customer_order_management.services.mappers.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    private Order order1;
    private Order order2;
    private Date date1;
    private Date date2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Calendar calendar = Calendar.getInstance();

        // Creando order1 con una fecha específica
        calendar.set(2023, Calendar.AUGUST, 1); // Recuerda que enero es 0
        date1 = calendar.getTime();

        // Creando order2 con una fecha específica
        calendar.set(2023, Calendar.AUGUST, 2);
        date2 = calendar.getTime();

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
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderServiceImpl.getAllOrders();

        assertNotNull(orders);
        assertEquals(2, orders.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testGetOrderById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));

        Order order = orderServiceImpl.getOrderById(1L);

        assertNotNull(order);
        assertEquals(date1, order.getOrderDate());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetOrderByIdNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> orderServiceImpl.getOrderById(1L));
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateOrder() {
        when(orderRepository.existsById(order1.getId())).thenReturn(false);
        when(orderRepository.save(order1)).thenReturn(order1);

        Order createdOrder = orderServiceImpl.createOrder(order1);

        assertNotNull(createdOrder);
        assertEquals(date1, createdOrder.getOrderDate());
        verify(orderRepository, times(1)).save(order1);
    }

    @Test
    public void testCreateOrderAlreadyExists() {
        when(orderRepository.existsById(order1.getId())).thenReturn(true);

        Order createdOrder = orderServiceImpl.createOrder(order1);

        assertNotNull(createdOrder);
        assertEquals(order1, createdOrder);
        verify(orderRepository, times(0)).save(order1);
    }

    @Test
    public void testDeleteOrder() {
        when(orderRepository.existsById(1L)).thenReturn(true);

        orderServiceImpl.deleteOrder(1L);

        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteOrderNotFound() {
        when(orderRepository.existsById(1L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> orderServiceImpl.deleteOrder(1L));
    }

    @Test
    public void testUpdateOrder() {
        when(orderRepository.existsById(1L)).thenReturn(true);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));
        when(orderRepository.save(order1)).thenReturn(order1);

        Order updatedOrder = orderServiceImpl.updateOrder(1L, order1);

        assertNotNull(updatedOrder);
        assertEquals(date1, updatedOrder.getOrderDate());
        verify(orderRepository, times(1)).save(order1);
    }

    @Test
    public void testUpdateOrderNotFound() {
        when(orderRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> orderServiceImpl.updateOrder(1L, order1));
    }
}
