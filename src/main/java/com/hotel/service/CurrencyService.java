package com.hotel.service;

import com.hotel.entity.Currency;
import com.hotel.dto.CurrencyRequest;

public interface CurrencyService {
    Currency createCurrency(CurrencyRequest request);
    Currency updateCurrency(Integer id, CurrencyRequest request);
    void deleteCurrency(Integer id);
}