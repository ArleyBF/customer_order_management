package com.BF.customer_order_management.services.implementation;

import com.BF.customer_order_management.exceptions.NotFoundException;
import com.BF.customer_order_management.models.Order;
import com.BF.customer_order_management.repositories.OrderRepository;
import com.BF.customer_order_management.services.OrderService;
import com.BF.customer_order_management.services.mappers.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList=orderRepository.findAll();
        if(orderList.isEmpty()){
            throw new NotFoundException("LA LISTA ESTA VACIA");
        }
        return orderList;
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Order createOrder(Order order) {
        if (orderRepository.existsById(order.getId())){
            return order;
        }
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)){
            throw new NotFoundException();
        }
        orderRepository.deleteById(id);
    }

    @Override
    public Order updateOrder(Long id, Order updatedOrder) {
        if (orderRepository.existsById(id)){
            Order actualOrder = orderRepository.findById(id).get();
            entityMapper.updateOrderRegister(actualOrder,updatedOrder);
            return orderRepository.save(actualOrder);
        }
        throw new EntityNotFoundException("Cliente no encontrado con ID: " + id);
    }
}
