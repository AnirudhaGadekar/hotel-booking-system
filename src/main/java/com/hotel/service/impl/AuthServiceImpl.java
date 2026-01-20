package com.hotel.service.impl;

import com.hotel.entity.Admin;
import com.hotel.repository.AdminRepository;
import com.hotel.service.AuthService;
import com.hotel.util.PasswordUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private PasswordUtil passwordUtil;
    
    @Override
    public Optional<Admin> login(String email, String password, HttpSession session) {
        String hashedPassword = passwordUtil.hashPassword(password);
        Optional<Admin> admin = adminRepository.findByEmail(email);
        
        if (admin.isPresent() && admin.get().getPassword().equals(hashedPassword)) {
            // Set session attributes (matching PHP $_SESSION)
            session.setAttribute("id", admin.get().getId());
            session.setAttribute("username", admin.get().getUsername());
            session.setAttribute("email", admin.get().getEmail());
            session.setAttribute("password", admin.get().getPassword());
            session.setAttribute("fname", admin.get().getFname());
            session.setAttribute("lname", admin.get().getLname());
            session.setAttribute("image", admin.get().getImage());
            
            return admin;
        }
        
        return Optional.empty();
    }
    
    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }
    
    @Override
    public boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("email") != null && 
               session.getAttribute("password") != null;
    }
}