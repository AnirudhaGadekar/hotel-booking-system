package com.hotel.controller;

import com.hotel.dto.ApiResponse;
import com.hotel.dto.CustomerRequest;
import com.hotel.entity.Customer;
import com.hotel.repository.CustomerRepository;
import com.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    /**
     * GET ALL CUSTOMERS (view_customer.php)
     * URL: GET /api/customers
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }
    
    /**
     * GET SINGLE CUSTOMER (for edit form)
     * URL: GET /api/customers/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {
        return customerRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * CREATE CUSTOMER (add_customer.php → pages/save_customer.php)
     * URL: POST /api/customers
     */
    @PostMapping
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody CustomerRequest request) {
        Customer customer = customerService.createCustomer(request);
        return ResponseEntity.ok(ApiResponse.success("Customer Added Successfully", customer));
    }
    
    /**
     * UPDATE CUSTOMER (edit_customer.php)
     * URL: PUT /api/customers/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable Integer id, @RequestBody CustomerRequest request) {
        Customer customer = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(ApiResponse.success("Record Successfully Updated", customer));
    }
    
    /**
     * DELETE CUSTOMER (del_customer.php)
     * URL: DELETE /api/customers/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(ApiResponse.success("Record Successfully Deleted", null));
    }
}
