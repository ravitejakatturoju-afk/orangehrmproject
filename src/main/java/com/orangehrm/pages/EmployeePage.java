package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class EmployeePage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By pimMenu = By.xpath("//span[text()='PIM']");
    private By addButton = By.xpath("//button[normalize-space()='Add']");
    private By firstNameField = By.name("firstName");
    private By lastNameField = By.name("lastName");
    private By saveButton = By.xpath("//button[@type='submit']");
    private By dashboardMenu = By.xpath("//span[text()='Dashboard']");
    
    public EmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void navigateToPIM() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
        wait.until(ExpectedConditions.urlContains("pim"));
    }
    
    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        wait.until(ExpectedConditions.urlContains("addEmployee"));
    }
    
    public void enterEmployeeDetails(String firstName, String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).clear();
        driver.findElement(firstNameField).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).clear();
        driver.findElement(lastNameField).sendKeys(lastName);
    }
    
    public void saveEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));
    }
    
    public void navigateToDashboard() {
        wait.until(ExpectedConditions.elementToBeClickable(dashboardMenu)).click();
        wait.until(ExpectedConditions.urlContains("dashboard"));
    }
    
    public boolean isEmployeeSaved() {
        try {
            wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}