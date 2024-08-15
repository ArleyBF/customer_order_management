package com.BF.customer_order_management.services.implementation;

import com.BF.customer_order_management.exceptions.NotFoundException;
import com.BF.customer_order_management.models.Customer;
import com.BF.customer_order_management.repositories.CustomerRepository;
import com.BF.customer_order_management.services.mappers.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BF.customer_order_management.services.CustomerService;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public List<Customer> getAllCustomers(){
        List<Customer> customerList = customerRepository.findAll();
        if(customerList.isEmpty()){
            throw new NotFoundException("LA LISTA ESTA VACIA");
        }
        return customerList;
    }

    @Override
    public Customer getCustomerByID(Long id){
        return customerRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Customer createCustomer(Customer customer){
        if (customerRepository.existsById(customer.getId())){
            return customer;
        }
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id){
        if (!customerRepository.existsById(id)){
            throw new NotFoundException();
        }
        customerRepository.deleteById(id);
    }

    @Override
    public Customer updateCustomer(Long id, Customer updatedCustomer){
        if (customerRepository.existsById(id)) {
            Customer actualCustomer= customerRepository.findById(id).get();
            entityMapper.updateCustomerRegister(actualCustomer,updatedCustomer);
            return customerRepository.save(actualCustomer);
        }
        throw new EntityNotFoundException("Cliente no encontrado con ID: " + id);
    }
}
