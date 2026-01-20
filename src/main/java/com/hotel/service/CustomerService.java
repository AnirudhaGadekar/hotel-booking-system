package com.hotel.service;

import com.hotel.entity.Customer;
import com.hotel.dto.CustomerRequest;

public interface CustomerService {
    Customer createCustomer(CustomerRequest request);
    Customer updateCustomer(Integer id, CustomerRequest request);
    void deleteCustomer(Integer id);
}

