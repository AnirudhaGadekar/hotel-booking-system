package com.hotel.service;

import com.hotel.entity.Discount;
import com.hotel.dto.DiscountRequest;

public interface DiscountService {
    Discount createDiscount(DiscountRequest request);
    Discount updateDiscount(Integer id, DiscountRequest request);
    void deleteDiscount(Integer id);
}
