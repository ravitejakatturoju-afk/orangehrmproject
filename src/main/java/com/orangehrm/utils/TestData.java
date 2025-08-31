package com.orangehrm.utils;

import java.util.Random;

public class TestData {
    private static final Random random = new Random();
    
    public static String generateUsername() {
        return "user" + System.currentTimeMillis();
    }
    
    public static String generateEmail() {
        return "test" + System.currentTimeMillis() + "@example.com";
    }
    
    public static String generatePassword() {
        return "Test@123" + random.nextInt(1000);
    }
    
    public static class ValidCredentials {
        public static final String USERNAME = "Admin";
        public static final String PASSWORD = "admin123";
    }
}