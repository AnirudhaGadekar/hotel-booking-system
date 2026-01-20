package com.hotel.service.impl;

import com.hotel.entity.Admin;
import com.hotel.repository.AdminRepository;
import com.hotel.dto.ProfileUpdateRequest;
import com.hotel.dto.ChangePasswordRequest;
import com.hotel.service.ProfileService;
import com.hotel.util.PasswordUtil;
import com.hotel.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class ProfileServiceImpl implements ProfileService {
    
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private PasswordUtil passwordUtil;
    
    @Autowired
    private FileUploadUtil fileUploadUtil;
    
    @Override
    @Transactional
    public Admin updateProfile(Integer adminId, ProfileUpdateRequest request, MultipartFile profileImage) {
        Admin admin = adminRepository.findById(adminId)
            .orElseThrow(() -> new RuntimeException("Admin not found"));
        
        admin.setFname(request.getFname());
        admin.setLname(request.getLname());
        admin.setEmail(request.getEmail());
        admin.setContact(request.getContact());
        admin.setDob(request.getDob());
        admin.setGender(request.getGender());
        
        // Handle profile image upload
        if (profileImage != null && !profileImage.isEmpty()) {
            try {
                String fileName = fileUploadUtil.uploadFile(profileImage, "Profile", admin.getImage());
                admin.setImage(fileName);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload profile image", e);
            }
        }
        
        return adminRepository.save(admin);
    }
    
    @Override
    @Transactional
    public void changePassword(Integer adminId, ChangePasswordRequest request) {
        Admin admin = adminRepository.findById(adminId)
            .orElseThrow(() -> new RuntimeException("Admin not found"));
        
        // Verify old password (matching PHP logic)
        String oldPasswordHash = passwordUtil.hashPassword(request.getOldPassword());
        if (!admin.getPassword().equals(oldPasswordHash)) {
            throw new RuntimeException("Old Password not matched");
        }
        
        // Verify new password matches confirmation
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("NEW Password and CONFIRM password not Matched");
        }
        
        // Update password
        String newPasswordHash = passwordUtil.hashPassword(request.getNewPassword());
        admin.setPassword(newPasswordHash);
        
        adminRepository.save(admin);
    }
}
