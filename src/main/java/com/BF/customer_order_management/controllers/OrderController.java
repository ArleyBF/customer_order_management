package com.BF.customer_order_management.controllers;

import com.BF.customer_order_management.exceptions.BadRequestException;
import com.BF.customer_order_management.models.Order;
import com.BF.customer_order_management.services.implementation.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @CrossOrigin
    @GetMapping("/all")
    public List<Order> getAllOrders(){
        return orderServiceImpl.getAllOrders();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        if(id==null || id==0){
            throw new BadRequestException(id);
        }
        Order order = orderServiceImpl.getOrderById(id);
        return ResponseEntity.ok().body(order);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        Order savedOrder = orderServiceImpl.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        if (id==null || id==0){
            throw new BadRequestException(id);
        }
        orderServiceImpl.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder){
       if (id==null || id==0){
            throw new BadRequestException(id);
       }
        Order savedOrder = orderServiceImpl.updateOrder(id,updatedOrder);
        return ResponseEntity.ok(savedOrder);
    }
}