package com.hotel.controller;

import com.hotel.dto.ApiResponse;
import com.hotel.dto.DiscountRequest;
import com.hotel.entity.Discount;
import com.hotel.repository.DiscountRepository;
import com.hotel.service.DiscountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {
    
    @Autowired
    private DiscountService discountService;
    
    @Autowired
    private DiscountRepository discountRepository;
    
    @GetMapping
    public ResponseEntity<List<Discount>> getAllDiscounts() {
        return ResponseEntity.ok(discountRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Discount> getDiscount(@PathVariable Integer id) {
        return discountRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse> createDiscount(@RequestBody DiscountRequest request, HttpSession session) {
        Discount discount = discountService.createDiscount(request);
        session.setAttribute("success", "Discount Added Successfully");
        return ResponseEntity.ok(ApiResponse.success("Discount Added Successfully", discount));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateDiscount(@PathVariable Integer id, @RequestBody DiscountRequest request, HttpSession session) {
        Discount discount = discountService.updateDiscount(id, request);
        session.setAttribute("success", "Record Successfully Updated");
        return ResponseEntity.ok(ApiResponse.success("Record Successfully Updated", discount));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDiscount(@PathVariable Integer id, HttpSession session) {
        discountService.deleteDiscount(id);
        session.setAttribute("success", "Record Successfully Deleted");
        return ResponseEntity.ok(ApiResponse.success("Record Successfully Deleted", null));
    }
}