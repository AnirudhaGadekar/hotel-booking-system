package com.hotel.controller;

import com.hotel.dto.ApiResponse;
import com.hotel.entity.ManageWebsite;
import com.hotel.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/website")
public class WebsiteController {

    @Autowired
    private WebsiteService websiteService;

    @GetMapping
    public ResponseEntity<ManageWebsite> getSettings() {
        return ResponseEntity.ok(websiteService.getSettings());
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateSettings(
            @RequestParam("title") String title,
            @RequestParam("currency_symbol") String currencySymbol,
            @RequestParam("footer") String footer,
            @RequestParam(value = "logo", required = false) MultipartFile logo,
            @RequestParam(value = "login_image", required = false) MultipartFile loginImage) {
        
        ManageWebsite updated = websiteService.updateSettings(title, currencySymbol, footer, logo, loginImage);
        return ResponseEntity.ok(ApiResponse.success("Website Settings Updated", updated));
    }
}