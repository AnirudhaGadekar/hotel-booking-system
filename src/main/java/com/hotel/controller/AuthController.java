package com.hotel.controller;

import com.hotel.dto.ApiResponse;
import com.hotel.dto.LoginRequest;
import com.hotel.dto.LoginResponse;
import com.hotel.dto.ChangePasswordRequest;
import com.hotel.dto.ProfileUpdateRequest;
import com.hotel.entity.Admin;
import com.hotel.service.AuthService;
import com.hotel.service.ProfileService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private ProfileService profileService;
    
    /**
     * LOGIN (login.php)
     * URL: POST /api/login
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request, HttpSession session) {
        var admin = authService.login(request.getEmail(), request.getPassword(), session);
        
        if (admin.isPresent()) {
            LoginResponse response = new LoginResponse();
            response.setSuccess(true);
            response.setMessage("Login Successfully");
            response.setId(admin.get().getId());
            response.setUsername(admin.get().getUsername());
            response.setEmail(admin.get().getEmail());
            response.setFname(admin.get().getFname());
            response.setLname(admin.get().getLname());
            response.setImage(admin.get().getImage());
            
            return ResponseEntity.ok(ApiResponse.success("Login Successfully", response));
        }
        
        return ResponseEntity.status(401).body(ApiResponse.error("Invalid Email or Password"));
    }
    
    /**
     * LOGOUT (logout.php)
     * URL: POST /api/logout
     */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(HttpSession session) {
        authService.logout(session);
        return ResponseEntity.ok(ApiResponse.success("Logout Successfully", null));
    }
    
    /**
     * CHECK AUTHENTICATION STATUS
     * URL: GET /api/check-auth
     */
    @GetMapping("/check-auth")
    public ResponseEntity<ApiResponse> checkAuth(HttpSession session) {
        boolean authenticated = authService.isAuthenticated(session);
        return ResponseEntity.ok(ApiResponse.success("", authenticated));
    }
    
    /**
     * UPDATE PROFILE (profile.php)
     * URL: POST /api/profile/update
     */
    @PostMapping("/profile/update")
    public ResponseEntity<ApiResponse> updateProfile(
            @RequestPart("data") ProfileUpdateRequest request,
            @RequestPart(value = "image", required = false) MultipartFile profileImage,
            HttpSession session) {
        
        Integer adminId = (Integer) session.getAttribute("id");
        if (adminId == null) {
            return ResponseEntity.status(401).body(ApiResponse.error("Unauthorized"));
        }
        
        Admin admin = profileService.updateProfile(adminId, request, profileImage);
        
        // Update session with new values
        session.setAttribute("fname", admin.getFname());
        session.setAttribute("lname", admin.getLname());
        session.setAttribute("email", admin.getEmail());
        session.setAttribute("image", admin.getImage());
        
        return ResponseEntity.ok(ApiResponse.success("Profile Updated Successfully", admin));
    }
    
    /**
     * CHANGE PASSWORD (changepassword.php)
     * URL: POST /api/change-password
     */
    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse> changePassword(
            @RequestBody ChangePasswordRequest request,
            HttpSession session) {
        
        Integer adminId = (Integer) session.getAttribute("id");
        if (adminId == null) {
            return ResponseEntity.status(401).body(ApiResponse.error("Unauthorized"));
        }
        
        try {
            profileService.changePassword(adminId, request);
            // Invalidate session after password change (matching PHP logic)
            session.invalidate();
            return ResponseEntity.ok(ApiResponse.success("Password Changed Successfully - Please login again", null));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}