package com.hotel.util;

import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class PasswordUtil {
    
    private static final String SALT = "2123293dsj2hu2nikhiljdsd";
    
    /**
     * Hash password using SHA-256 (matching PHP logic exactly)
     * 
     * PHP equivalent:
     * $passw = hash('sha256', $password);
     * $salt = '2123293dsj2hu2nikhiljdsd';
     * $pass = hash('sha256', $salt . $passw);
     */
    public String hashPassword(String password) {
        try {
            // First hash
            MessageDigest md1 = MessageDigest.getInstance("SHA-256");
            byte[] hash1 = md1.digest(password.getBytes(StandardCharsets.UTF_8));
            String firstHash = bytesToHex(hash1);
            
            // Second hash with salt (matching PHP logic)
            String saltedPassword = SALT + firstHash;
            MessageDigest md2 = MessageDigest.getInstance("SHA-256");
            byte[] hash2 = md2.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
            
            return bytesToHex(hash2);
        } catch (Exception e) {
            throw new RuntimeException("Password hashing failed", e);
        }
    }
    
    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}