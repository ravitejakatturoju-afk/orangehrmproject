package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//p[contains(@class,'alert-content-text')]");
    private By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void enterUsername(String username) {
        WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }
    
    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }
    
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        handlePasswordSavePopup();
    }
    
    public void handlePasswordSavePopup() {
        // Handle "Change your password" popup with shorter wait
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            By okButton = By.xpath("//button[text()='OK']");
            WebElement okBtn = shortWait.until(ExpectedConditions.elementToBeClickable(okButton));
            okBtn.click();
            Thread.sleep(1000); // Brief pause after clicking
        } catch (Exception e) {
            // Popup not present
        }
        
        // Handle "Save Password" popup with shorter wait
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            By neverButton = By.xpath("//button[text()='Never']");
            WebElement neverBtn = shortWait.until(ExpectedConditions.elementToBeClickable(neverButton));
            neverBtn.click();
            Thread.sleep(1000); // Brief pause after clicking
        } catch (Exception e) {
            // Popup not present
        }
    }
    
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }
    
    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.urlContains("dashboard"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isDashboardDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    // Before Login Assertions
    public boolean isOnLoginPage() {
        return getCurrentUrl().contains("auth/login");
    }
    
    public boolean areLoginFieldsVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).isDisplayed() &&
                   driver.findElement(passwordField).isDisplayed() &&
                   driver.findElement(loginButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    // After Login Assertions
    public boolean isRedirectedToDashboard() {
        try {
            wait.until(ExpectedConditions.urlContains("dashboard"));
            return getCurrentUrl().contains("dashboard/index");
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isUserAuthenticated() {
        try {
            By userDropdown = By.className("oxd-userdropdown-tab");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(userDropdown)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}