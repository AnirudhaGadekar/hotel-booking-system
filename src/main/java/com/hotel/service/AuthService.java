package com.hotel.service;

import com.hotel.entity.Admin;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

public interface AuthService {
    Optional<Admin> login(String email, String password, HttpSession session);
    void logout(HttpSession session);
    boolean isAuthenticated(HttpSession session);
}