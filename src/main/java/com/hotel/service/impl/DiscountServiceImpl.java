package com.hotel.service.impl;

import com.hotel.entity.Discount;
import com.hotel.repository.DiscountRepository;
import com.hotel.dto.DiscountRequest;
import com.hotel.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscountServiceImpl implements DiscountService {
    
    @Autowired
    private DiscountRepository discountRepository;
    
    @Override
    @Transactional
    public Discount createDiscount(DiscountRequest request) {
        Discount discount = new Discount();
        discount.setDiscountname(request.getDiscountname());
        discount.setPercentage(request.getPercentage());
        discount.setExpirydate(request.getExpirydate());
        
        return discountRepository.save(discount);
    }
    
    @Override
    @Transactional
    public Discount updateDiscount(Integer id, DiscountRequest request) {
        Discount discount = discountRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Discount not found"));
        
        discount.setDiscountname(request.getDiscountname());
        discount.setPercentage(request.getPercentage());
        discount.setExpirydate(request.getExpirydate());
        
        return discountRepository.save(discount);
    }
    
    @Override
    @Transactional
    public void deleteDiscount(Integer id) {
        discountRepository.deleteById(id);
    }
}