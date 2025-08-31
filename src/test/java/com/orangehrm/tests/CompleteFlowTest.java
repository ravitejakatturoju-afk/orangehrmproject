package com.orangehrm.tests;

import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.EmployeePage;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CompleteFlowTest extends BaseTest {
    
    @Test(description = "Complete flow: Launch browser, maximize, login, add employee, return to dashboard")
    public void testCompleteEmployeeFlow() {
        LoginPage loginPage = new LoginPage(driver);
        EmployeePage employeePage = new EmployeePage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        
        // Step 1: Verify browser launch and maximize
        Assert.assertTrue(loginPage.isOnLoginPage(), "Browser should launch and navigate to login page");
        Assert.assertTrue(loginPage.areLoginFieldsVisible(), "All login fields should be visible after page load");
        Assert.assertTrue(loginPage.getPageTitle().contains("OrangeHRM"), "Page title should contain 'OrangeHRM'");
        
        // Step 2: Login with valid credentials
        loginPage.login(TestData.ValidCredentials.USERNAME, TestData.ValidCredentials.PASSWORD);
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful with valid credentials");
        Assert.assertTrue(loginPage.isRedirectedToDashboard(), "Should be redirected to dashboard after login");
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard header should be visible");
        Assert.assertTrue(loginPage.isUserAuthenticated(), "User should be authenticated with dropdown visible");
        
        // Step 3: Navigate to PIM module
        employeePage.navigateToPIM();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("pim"), "Should navigate to PIM module");
        
        // Step 4: Add new employee
        employeePage.clickAddEmployee();
        Assert.assertTrue(driver.getCurrentUrl().contains("addEmployee"), "Should navigate to add employee page");
        
        employeePage.enterEmployeeDetails("John", "Smith");
        employeePage.saveEmployee();
        Assert.assertTrue(employeePage.isEmployeeSaved(), "Employee should be saved successfully");
        Assert.assertTrue(driver.getCurrentUrl().contains("viewPersonalDetails"), "Should navigate to employee details page");
        
        // Step 5: Return to dashboard
        employeePage.navigateToDashboard();
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Should return to dashboard successfully");
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "URL should contain 'dashboard'");
        Assert.assertTrue(dashboardPage.isUserLoggedIn(), "User should still be logged in on dashboard");
    }
}