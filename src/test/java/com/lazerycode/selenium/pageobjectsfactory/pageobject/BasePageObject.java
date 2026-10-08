package com.lazerycode.selenium.pageobjectsfactory.pageobject;

import com.lazerycode.selenium.DriverBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePageObject {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePageObject() throws Exception {
        // Initialize driver from DriverBase
        this.driver = DriverBase.getDriver();
        // Set up a standard wait time of 15 seconds
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // Initialize @FindBy elements defined in child classes
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
}