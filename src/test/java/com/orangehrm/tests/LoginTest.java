package com.orangehrm.tests;

import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    
    @Test(priority = 1, description = "Test successful login with valid credentials")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Before Login Assertions
        Assert.assertTrue(loginPage.isOnLoginPage(), "Should be on login page before login");
        Assert.assertTrue(loginPage.areLoginFieldsVisible(), "Login fields should be visible");
        Assert.assertTrue(loginPage.getPageTitle().contains("OrangeHRM"), "Page title should contain OrangeHRM");
        
        // Perform login
        loginPage.login(TestData.ValidCredentials.USERNAME, TestData.ValidCredentials.PASSWORD);
        
        // After Login Assertions
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful with valid credentials");
        Assert.assertTrue(loginPage.isRedirectedToDashboard(), "Should be redirected to dashboard page");
        Assert.assertTrue(loginPage.isDashboardDisplayed(), "Dashboard header should be visible after login");
        Assert.assertTrue(loginPage.isUserAuthenticated(), "User should be authenticated with dropdown visible");
    }
    
    @Test(priority = 2, description = "Test login failure with invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.login("invalid", "invalid");
        
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Invalid credentials", "Error message should match expected text");
        Assert.assertFalse(loginPage.isLoginSuccessful(), "Login should fail with invalid credentials");
    }
    
    @Test(priority = 3, description = "Test login failure with empty credentials")
    public void testEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.login("", "");
        
        Assert.assertFalse(loginPage.isLoginSuccessful(), "Login should fail with empty credentials");
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("auth/login"), "Should remain on login page with empty credentials");
    }
}