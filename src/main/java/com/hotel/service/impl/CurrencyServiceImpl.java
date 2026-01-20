package com.hotel.service.impl;

import com.hotel.entity.Currency;
import com.hotel.repository.CurrencyRepository;
import com.hotel.dto.CurrencyRequest;
import com.hotel.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    
    @Autowired
    private CurrencyRepository currencyRepository;
    
    @Override
    @Transactional
    public Currency createCurrency(CurrencyRequest request) {
        Currency currency = new Currency();
        currency.setCurrcode(request.getCurrcode());
        currency.setCurrsymbol(request.getCurrsymbol());
        
        return currencyRepository.save(currency);
    }
    
    @Override
    @Transactional
    public Currency updateCurrency(Integer id, CurrencyRequest request) {
        Currency currency = currencyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Currency not found"));
        
        currency.setCurrcode(request.getCurrcode());
        currency.setCurrsymbol(request.getCurrsymbol());
        
        return currencyRepository.save(currency);
    }
    
    @Override
    @Transactional
    public void deleteCurrency(Integer id) {
        currencyRepository.deleteById(id);
    }
}