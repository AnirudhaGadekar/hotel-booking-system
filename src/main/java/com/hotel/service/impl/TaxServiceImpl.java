package com.hotel.service.impl;

import com.hotel.entity.Tax;
import com.hotel.repository.TaxRepository;
import com.hotel.dto.TaxRequest;
import com.hotel.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaxServiceImpl implements TaxService {
    
    @Autowired
    private TaxRepository taxRepository;
    
    @Override
    @Transactional
    public Tax createTax(TaxRequest request) {
        Tax tax = new Tax();
        tax.setTaxname(request.getTaxname());
        tax.setPercentage(request.getPercentage());
        tax.setShortcode(request.getShortcode());
        
        return taxRepository.save(tax);
    }
    
    @Override
    @Transactional
    public Tax updateTax(Integer id, TaxRequest request) {
        Tax tax = taxRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tax not found"));
        
        tax.setTaxname(request.getTaxname());
        tax.setPercentage(request.getPercentage());
        tax.setShortcode(request.getShortcode());
        
        return taxRepository.save(tax);
    }
    
    @Override
    @Transactional
    public void deleteTax(Integer id) {
        taxRepository.deleteById(id);
    }
}