package com.hotel.controller;

import com.hotel.dto.ApiResponse;
import com.hotel.dto.CurrencyRequest;
import com.hotel.entity.Currency;
import com.hotel.repository.CurrencyRepository;
import com.hotel.service.CurrencyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {
    
    @Autowired
    private CurrencyService currencyService;
    
    @Autowired
    private CurrencyRepository currencyRepository;
    
    @GetMapping
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        return ResponseEntity.ok(currencyRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Currency> getCurrency(@PathVariable Integer id) {
        return currencyRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse> createCurrency(@RequestBody CurrencyRequest request, HttpSession session) {
        Currency currency = currencyService.createCurrency(request);
        session.setAttribute("success", "Currency Added Successfully");
        return ResponseEntity.ok(ApiResponse.success("Currency Added Successfully", currency));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCurrency(@PathVariable Integer id, @RequestBody CurrencyRequest request, HttpSession session) {
        Currency currency = currencyService.updateCurrency(id, request);
        session.setAttribute("success", "Record Successfully Updated");
        return ResponseEntity.ok(ApiResponse.success("Record Successfully Updated", currency));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCurrency(@PathVariable Integer id, HttpSession session) {
        currencyService.deleteCurrency(id);
        session.setAttribute("success", "Record Successfully Deleted");
        return ResponseEntity.ok(ApiResponse.success("Record Successfully Deleted", null));
    }
}