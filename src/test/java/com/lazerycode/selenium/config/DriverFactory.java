package com.lazerycode.selenium.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private WebDriver webdriver;

    public WebDriver getDriver() {
        if (null == webdriver) {
            String browser = System.getProperty("browser", "chrome").toLowerCase();

            if (browser.equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                webdriver = new FirefoxDriver(options);
            } else {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                webdriver = new ChromeDriver(options);
            }
        }
        return webdriver;
    }

//    public void quitDriver() {
//        if (null != webdriver) {
//            webdriver.quit();
//        }
//    }
}