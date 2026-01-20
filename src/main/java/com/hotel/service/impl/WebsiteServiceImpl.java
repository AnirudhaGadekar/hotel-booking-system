package com.hotel.service.impl;

import com.hotel.entity.ManageWebsite;
import com.hotel.repository.ManageWebsiteRepository;
import com.hotel.service.WebsiteService;
import com.hotel.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service // ✅ This annotation is Critical. It tells Spring "I am the bean you are looking for"
public class WebsiteServiceImpl implements WebsiteService {

    @Autowired
    private ManageWebsiteRepository websiteRepository;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Override
    public ManageWebsite getSettings() {
        List<ManageWebsite> settings = websiteRepository.findAll();
        if (settings.isEmpty()) {
            return null; // Or return new ManageWebsite();
        }
        return settings.get(0);
    }

    @Override
    public ManageWebsite updateSettings(String title, String currencySymbol, String footer, MultipartFile logo, MultipartFile loginImage) {
        ManageWebsite existing = getSettings();
        if (existing == null) {
            existing = new ManageWebsite();
        }

        existing.setTitle(title);
        existing.setCurrency_symbol(currencySymbol);
        existing.setFooter(footer);

        try {
            if (logo != null && !logo.isEmpty()) {
                String logoName = fileUploadUtil.uploadFile(logo, "website", existing.getLogo());
                existing.setLogo(logoName);
            }
            if (loginImage != null && !loginImage.isEmpty()) {
                String loginName = fileUploadUtil.uploadFile(loginImage, "website", existing.getLogin_image());
                existing.setLogin_image(loginName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error saving website images", e);
        }

        return websiteRepository.save(existing);
    }
}