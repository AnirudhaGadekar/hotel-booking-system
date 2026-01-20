package com.hotel.service;

import com.hotel.entity.Admin;
import com.hotel.dto.ProfileUpdateRequest;
import com.hotel.dto.ChangePasswordRequest;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {
    Admin updateProfile(Integer adminId, ProfileUpdateRequest request, MultipartFile profileImage);
    void changePassword(Integer adminId, ChangePasswordRequest request);
}
