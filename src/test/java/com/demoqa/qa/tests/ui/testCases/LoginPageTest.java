//package com.demoqa.qa.tests.ui.testCases;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;
//import org.testng.annotations.*;
//import com.demoqa.qa.tests.ui.pages.LoginPage;
//
//public class LoginPageTest extends ToolsQaTest {
//
//    @Test
//    public void testLogin() {
//        String login = "TestUsername1";
//        String password = "TestPass1!";
//        driver.get(baseUrl + "/login");
//        LoginPage loginPage = new LoginPage(driver);
//
//
//        try {
//            dismissDataConsentModal();
//            loginPage.login(login, password);
//
//
//
//            sendKeysToElement(Locators.LoginLocators.PASSWORD, password);
//
//            WebElement loginButton = driver.findElement(By.id("login"));
//            loginButton.click();
//            wait.until(ExpectedConditions.stalenessOf(loginButton));
//
//            WebElement searchBox = driver.findElement(By.id("searchBox"));
//            wait.until(ExpectedConditions.visibilityOf(searchBox));
//
//            Assert.assertEquals(driver.getCurrentUrl(), base_url + "/profile");
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    public void testForm() {
//        String name = "QA";
//        String surname = "Team";
//        String email = "qa.mail@gmail.com";
//        String mobile = "4111111111";
//        driver.get(baseUrl + "/automation-practice-form");
//
//        try {
//            sendKeysToElement(Locators.FormLocators.FIRST_NAME, name);
//            sendKeysToElement(Locators.FormLocators.LAST_NAME, surname);
//            sendKeysToElement(Locators.FormLocators.USER_EMAIL, email);
//            clickElement(Locators.FormLocators.GENDER_RADIO);
//            sendKeysToElement(Locators.FormLocators.USER_NUMBER, mobile);
//            clickElement(Locators.FormLocators.SUBMIT_BUTTON);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void dismissDataConsentModal() {
//        WebElement dataUseModalText = driver.findElement(By.cssSelector("body > div.fc-consent-root > div.fc-dialog-container > div.fc-dialog.fc-choice-dialog > div.fc-dialog-content > div > div.fc-header.fc-dialog-restricted-content > h1"));
//        wait.until(ExpectedConditions.visibilityOf(dataUseModalText));
//
//        WebElement consentDataUseButton = driver.findElement(By.cssSelector("body > div.fc-consent-root > div.fc-dialog-container > div.fc-dialog.fc-choice-dialog > div.fc-footer-buttons-container > div.fc-footer-buttons > button.fc-button.fc-cta-consent.fc-primary-button"));
//        wait.until(ExpectedConditions.visibilityOf(consentDataUseButton));
//        consentDataUseButton.click();
//    }
//
//    private void sendKeysToElement(By locator, String keysToSend) {
//        WebElement element = driver.findElement(locator);
//        wait.until(ExpectedConditions.visibilityOf(element));
//        element.sendKeys(keysToSend);
//    }
//
//    private void clickElement(By locator) {
//        WebElement element = driver.findElement(locator);
//        wait.until(ExpectedConditions.visibilityOf(element));
//        element.click();
//    }
//}