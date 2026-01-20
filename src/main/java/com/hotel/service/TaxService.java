package com.hotel.service;

import com.hotel.entity.Tax;
import com.hotel.dto.TaxRequest;

public interface TaxService {
    Tax createTax(TaxRequest request);
    Tax updateTax(Integer id, TaxRequest request);
    void deleteTax(Integer id);
}