package com.orangehrm.tests;

import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.utils.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {
    
    @BeforeMethod
    public void loginBeforeTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.ValidCredentials.USERNAME, TestData.ValidCredentials.PASSWORD);
    }
    
    @Test(priority = 1)
    public void testDashboardTitle() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        
        String pageTitle = dashboardPage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("OrangeHRM"), "Page title should contain OrangeHRM");
    }
    
    @Test(priority = 2)
    public void testDashboardElements() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard header should be visible");
        Assert.assertTrue(dashboardPage.isUserLoggedIn(), "User dropdown should be visible");
    }
}