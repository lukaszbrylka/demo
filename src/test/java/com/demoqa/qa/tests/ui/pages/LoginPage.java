package com.demoqa.qa.tests.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class LoginPage {
    private RemoteWebDriver driver;


    @FindBy(id = "userName")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    // Constructor
    public LoginPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        // Add logic to click the login button or submit the form
    }

}
