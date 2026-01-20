package com.hotel.controller;

import com.hotel.dto.ApiResponse;
import com.hotel.dto.TaxRequest;
import com.hotel.entity.Tax;
import com.hotel.repository.TaxRepository;
import com.hotel.service.TaxService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/taxes")
public class TaxController {
    
    @Autowired
    private TaxService taxService;
    
    @Autowired
    private TaxRepository taxRepository;
    
    @GetMapping
    public ResponseEntity<List<Tax>> getAllTaxes() {
        return ResponseEntity.ok(taxRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Tax> getTax(@PathVariable Integer id) {
        return taxRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse> createTax(@RequestBody TaxRequest request, HttpSession session) {
        Tax tax = taxService.createTax(request);
        session.setAttribute("success", "Tax Added Successfully");
        return ResponseEntity.ok(ApiResponse.success("Tax Added Successfully", tax));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTax(@PathVariable Integer id, @RequestBody TaxRequest request, HttpSession session) {
        Tax tax = taxService.updateTax(id, request);
        session.setAttribute("success", "Record Successfully Updated");
        return ResponseEntity.ok(ApiResponse.success("Record Successfully Updated", tax));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTax(@PathVariable Integer id, HttpSession session) {
        taxService.deleteTax(id);
        session.setAttribute("success", "Record Successfully Deleted");
        return ResponseEntity.ok(ApiResponse.success("Record Successfully Deleted", null));
    }
}