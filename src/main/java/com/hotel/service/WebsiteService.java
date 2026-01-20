package com.hotel.service;

import com.hotel.entity.ManageWebsite;
import org.springframework.web.multipart.MultipartFile;

public interface WebsiteService {
    ManageWebsite getSettings();
    
    ManageWebsite updateSettings(
        String title, 
        String currencySymbol, 
        String footer, 
        MultipartFile logo, 
        MultipartFile loginImage
    );
}