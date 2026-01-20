package com.hotel.service.impl;

import com.hotel.entity.Customer;
import com.hotel.repository.CustomerRepository;
import com.hotel.dto.CustomerRequest;
import com.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public Customer createCustomer(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setGender(request.getGender());
        
        // Ensure Customer.java Entity has these fields!
        customer.setBirthdate(request.getBirthdate()); 
        customer.setContact(request.getContact());
        
        customer.setAddress(request.getAddress());
        customer.setCreatedDate(LocalDate.now());
        
        return customerRepository.save(customer);
    }
    
    @Override
    public Customer updateCustomer(Integer id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setGender(request.getGender());
        
        // Ensure Customer.java Entity has these fields!
        customer.setBirthdate(request.getBirthdate());
        customer.setContact(request.getContact());
        
        customer.setAddress(request.getAddress());
        
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}