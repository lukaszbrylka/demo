package com.demoqa.qa.tests.ui.testCases;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;


public class ToolsQaTest {
    protected RemoteWebDriver driver;
    public WebDriverWait wait;
    private final String DEFAULT_BROWSER = "chrome";
    private static final String WEBDRIVER_TYPE = "local";
    private static final String REMOTE_WEBDRIVER_URL = "http://localhost:4444/wd/hub";
    public String baseUrl = "https://demoqa.com";

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--force-device-scale-factor=0.80");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--ignore-ssl-errors=yes");
        options.addArguments("--ignore-certificate-errors");

        return options;
    }

    @BeforeTest
    @Parameters({"webDriverType", "browser", "version", "remoteWebDriverUrl"})
    public void setup(@Optional String webDriverType, @Optional String browser, @Optional String version, @Optional String remoteWebDriverUrl) throws MalformedURLException {
        if (WEBDRIVER_TYPE.equals("local")) {
            Path chromeDriverPath = Paths.get(System.getProperty("user.home"), "chromedriver-dir", "chromedriver");
            ChromeOptions options = getChromeOptions();
            System.setProperty("webdriver.chrome.driver", chromeDriverPath.toString());
            driver = new ChromeDriver(options);

        } else if (WEBDRIVER_TYPE.equals("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser != null ? browser : DEFAULT_BROWSER);
            driver = new RemoteWebDriver(new URL(REMOTE_WEBDRIVER_URL), capabilities);

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}