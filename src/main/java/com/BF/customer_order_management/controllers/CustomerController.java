package com.BF.customer_order_management.controllers;

import com.BF.customer_order_management.exceptions.BadRequestException;
import com.BF.customer_order_management.models.Customer;
import com.BF.customer_order_management.services.implementation.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @CrossOrigin
    @GetMapping("/all")
    public List<Customer> getAllCustomers(){
        return customerServiceImpl.getAllCustomers();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        if(id==null || id==0){
            throw new BadRequestException(id);
        }
        Customer customer = customerServiceImpl.getCustomerByID(id);
        return ResponseEntity.ok().body(customer);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer savedCustomer = customerServiceImpl.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        if(id==null || id==0){
            throw new BadRequestException(id);
        }
       customerServiceImpl.deleteCustomer(id);
       return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,@RequestBody Customer updatedCustomer){
        if(id==null || id==0){
            throw new BadRequestException(id);
        }
        Customer savedCustomer = customerServiceImpl.updateCustomer(id,updatedCustomer);
        return ResponseEntity.ok(savedCustomer);
    }
}