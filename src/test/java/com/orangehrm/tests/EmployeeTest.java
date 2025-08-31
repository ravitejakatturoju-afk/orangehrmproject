package com.orangehrm.tests;

import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.EmployeePage;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeTest extends BaseTest {
    
    @Test(description = "Login, add employee and return to dashboard")
    public void testAddEmployeeFlow() {
        LoginPage loginPage = new LoginPage(driver);
        EmployeePage employeePage = new EmployeePage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        
        // Login
        loginPage.login(TestData.ValidCredentials.USERNAME, TestData.ValidCredentials.PASSWORD);
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
        
        // Navigate to PIM and add employee
        employeePage.navigateToPIM();
        employeePage.clickAddEmployee();
        employeePage.enterEmployeeDetails("John", "Doe");
        employeePage.saveEmployee();
        
        // Verify employee is saved
        Assert.assertTrue(employeePage.isEmployeeSaved(), "Employee should be saved successfully");
        
        // Return to dashboard
        employeePage.navigateToDashboard();
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Should return to dashboard");
    }
}